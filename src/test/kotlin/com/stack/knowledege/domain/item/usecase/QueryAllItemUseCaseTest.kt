package com.stack.knowledege.domain.item.usecase

import com.stack.knowledege.domain.item.application.spi.QueryItemPort
import com.stack.knowledege.domain.item.application.usecase.QueryAllItemUseCase
import com.stack.knowledege.domain.item.domain.Item
import com.stack.knowledege.domain.item.persistence.entity.ItemJpaEntity
import com.stack.knowledege.domain.item.persistence.repository.ItemJpaRepository
import com.stack.knowledege.domain.item.presentation.data.response.ItemResponse
//import com.stack.knowledege.global.annotation.StackKnowledgeTest
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.Test
import org.springframework.test.context.junit.jupiter.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.cache.annotation.EnableCaching
import java.util.UUID

//@StackKnowledgeTest
//@SpringBootTest
@SpringBootTest
@EnableCaching
@ExtendWith(SpringExtension::class)
class QueryAllItemUseCaseTest : BehaviorSpec({
    val queryItemPort = mockk<QueryItemPort>()
    val queryAllItemUseCase = QueryAllItemUseCase(queryItemPort)

    Given("QueryAllItemUseCaseTest") {
        val itemId1 = UUID.randomUUID()
        val itemName1 = "외출증"
        val itemPrice1 = 5000
        val itemId2 = UUID.randomUUID()
        val itemName2 = "간식"
        val itemPrice2 = 1000

        When("캐시가 없는 상태에서 execute() 호출") {
            val items = listOf(
                Item(id = itemId1, name = itemName1, price = itemPrice1),
                Item(id = itemId2, name = itemName2, price = itemPrice2)
            )

            every { queryItemPort.queryAllItem() } returns items

            Then("QueryItemPort 의 queryAllItem()이 한 번만 호출되고 캐싱됨") {
                repeat(5) { queryAllItemUseCase.execute() }
                verify(exactly = 1) {queryItemPort.queryAllItem() }
            }


            Then("알맞은 결과를 반환함") {
                val response = listOf(
                    ItemResponse(id = itemId1, name = itemName1, price = itemPrice1),
                    ItemResponse(id = itemId2, name = itemName2, price = itemPrice2)
                )
                val result = queryAllItemUseCase.execute()
                result shouldBe response
            }
        }
    }
})
