package com.stack.knowledege.domain.item.usecase

import com.stack.knowledege.domain.item.application.spi.QueryItemPort
import com.stack.knowledege.domain.item.application.usecase.QueryAllItemUseCase
import com.stack.knowledege.domain.item.domain.Item
import com.stack.knowledege.domain.item.presentation.data.response.ItemResponse
import com.stack.knowledege.global.annotation.StackKnowledgeTest
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.cache.Cache
import org.springframework.cache.CacheManager
import java.util.UUID


@StackKnowledgeTest
class QueryAllItemUseCaseTest : BehaviorSpec({
    val queryItemPort = mockk<QueryItemPort>()
    val cacheManager = mockk<CacheManager>()
    val cache = mockk<Cache>()


    Given("QueryAllItemUseCaseTest") {
        every { cacheManager.getCache(any()) } returns cache

        val queryAllItemUseCase = QueryAllItemUseCase(queryItemPort)

        val itemId1 = UUID.randomUUID()
        val itemName1 = "외출증"
        val itemPrice1 = 5000
        val itemId2 = UUID.randomUUID()
        val itemName2 = "간식"
        val itemPrice2 = 1000

        When("캐시에 저장되어 있지 않은 상태에서 호출") {

            every { cache.get(any()) } returns null

            val items = listOf(
                Item(id = itemId1, name = "외출증", price = 5000),
                Item(id = itemId2, name = "간식", price = 1000)
            )

            every { queryItemPort.queryAllItem() } returns items

            val result = queryAllItemUseCase.execute()

            Then("queryAllItem 이 한 번 호출됨") {
                verify(exactly = 1) { queryItemPort.queryAllItem() }
            }

            Then("결과가 캐싱") {
                verify(exactly = 1) { cache.put(any(), any()) }
            }

            Then("정상적인 값을 반환함") {
                val response = listOf(
                    ItemResponse(id = itemId1, name = itemName1, price = itemPrice1),
                    ItemResponse(id = itemId2, name = itemName2, price = itemPrice2)
                )
                result shouldBe response
            }
        }

        When("캐시가 있는 상태에서 execute() 호출") {
            every { cache.get(any()) } returns mockk()
            val cachedItems = listOf(
                ItemResponse(id = itemId1, name = itemName1, price = itemPrice1),
                ItemResponse(id = itemId2, name = itemName2, price = itemPrice2)
            )

            val result = queryAllItemUseCase.execute()

            Then("QueryItemPort 의 queryAllItem()이 호출되지 않음") {
                verify(exactly = 0) { queryItemPort.queryAllItem() }
            }

            Then("캐시된 결과를 반환함") {
                result shouldBe cachedItems
            }
        }
    }
})
