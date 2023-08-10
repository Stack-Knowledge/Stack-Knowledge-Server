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
import org.springframework.cache.Cache.ValueWrapper
import org.springframework.cache.annotation.Cacheable
import java.util.UUID


@StackKnowledgeTest
class QueryAllItemUseCaseTest : BehaviorSpec({
    val queryItemPort = mockk<QueryItemPort>()
    val cache = mockk<Cache>()
    val queryAllItemUseCase = QueryAllItemUseCase(queryItemPort)

    Given("QueryAllItemUseCaseTest") {
        val itemId1 = UUID.randomUUID()
        val itemName1 = "외출증"
        val itemPrice1 = 5000
        val itemId2 = UUID.randomUUID()
        val itemName2 = "간식"
        val itemPrice2 = 1000

        When("캐시가 없는 상태에서 execute() 호출") {
            every { cache.put("all", any()) } returns null

            val items = listOf(
                Item(id = itemId1, name = itemName1, price = itemPrice1),
                Item(id = itemId2, name = itemName2, price = itemPrice2)
            )

            every { queryItemPort.queryAllItem() } returns items

            val result = queryAllItemUseCase.execute()

            Then("QueryItemPort 의 queryAllItem()이 한 번만 호출되고 캐싱됨") {
                queryAllItemUseCase.execute()
                verify(exactly = 1) { cache.put("all", result) }
                verify(exactly = 1) { queryItemPort.queryAllItem() }

                repeat(5) {
                    verify(exactly = 0) { queryItemPort.queryAllItem() }
                }

            }


            Then("알맞은 결과를 반환함") {
                val response = listOf(
                    ItemResponse(id = itemId1, name = itemName1, price = itemPrice1),
                    ItemResponse(id = itemId2, name = itemName2, price = itemPrice2)
                )
                result shouldBe response
            }
        }
    }
})


//@StackKnowledgeTest
//class QueryAllItemUseCaseTest : BehaviorSpec({
//    val queryItemPort = mockk<QueryItemPort>()
//    val cache = mockk<Cache>()
//
//    Given("QueryAllItemUseCaseTest") {
//        val queryAllItemUseCase = QueryAllItemUseCase(queryItemPort)
//
//        val itemId1 = UUID.randomUUID()
//        val itemName1 = "Away Pass"
//        val itemPrice1 = 5000
//        val itemId2 = UUID.randomUUID()
//        val itemName2 = "Snacks"
//        val itemPrice2 = 1000
//
//        When("캐시가 없는 상태에서 execute() 호출") {
//            every { cache.get(any()) } returns null
//
//            val items = listOf(
//                Item(id = itemId1, name = itemName1, price = itemPrice1),
//                Item(id = itemId2, name = itemName2, price = itemPrice2)
//            )
//
//            every { queryItemPort.queryAllItem() } returns items
//
//            val result = queryAllItemUseCase.execute()
//
//            Then("queryItemPort의 queryAllItem()이 한 번 호출되고 캐시됨") {
//                verify(exactly = 1) { queryItemPort.queryAllItem() }
//                verify(exactly = 1) { cache.put("all", any()) }
//
//                repeat(5) {
//                    queryAllItemUseCase.execute()
//                }
//            }
//
//            Then("정확한 결과를 반환함") {
//                val response = listOf(
//                    ItemResponse(id = itemId1, name = itemName1, price = itemPrice1),
//                    ItemResponse(id = itemId2, name = itemName2, price = itemPrice2)
//                )
//                result shouldBe response
//            }
//        }
//
//        When("캐시가 있는 상태에서 execute() 호출") {
//            val cachedItems = listOf(
//                ItemResponse(id = itemId1, name = itemName1, price = itemPrice1),
//                ItemResponse(id = itemId2, name = itemName2, price = itemPrice2)
//            )
//
//            every { queryItemPort.queryAllItem() } returns emptyList()
//            every { cache.get("all") } returns ValueWrapper { cachedItems }
//
//            val result = queryAllItemUseCase.execute()
//
//            Then("queryItemPort의 queryAllItem()이 호출되지 않음") {
//                verify(exactly = 0) { queryItemPort.queryAllItem() }
//            }
//
//            Then("캐시된 결과를 반환함") {
//                result shouldBe cachedItems
//            }
//        }
//    }
//})