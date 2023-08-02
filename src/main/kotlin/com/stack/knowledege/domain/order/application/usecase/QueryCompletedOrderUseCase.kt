package com.stack.knowledege.domain.order.application.usecase

import com.stack.knowledege.domain.common.annotation.usecase.ReadOnlyUseCase
import com.stack.knowledege.domain.item.application.spi.QueryItemPort
import com.stack.knowledege.domain.item.exception.ItemNotFoundException
import com.stack.knowledege.domain.item.presentation.data.response.ItemResponse
import com.stack.knowledege.domain.order.application.spi.QueryOrderPort
import com.stack.knowledege.domain.order.domain.constant.OrderStatus
import com.stack.knowledege.domain.order.presentation.data.response.CompletedOrderResponse
import com.stack.knowledege.domain.student.application.spi.QueryStudentPort
import com.stack.knowledege.domain.student.exception.StudentNotFoundException
import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.domain.user.presentation.data.response.UserResponse

@ReadOnlyUseCase
class QueryCompletedOrderUseCase(
    private val queryOrderPort: QueryOrderPort,
    private val queryItemPort: QueryItemPort,
    private val queryUserPort: QueryUserPort,
    private val queryStudentPort: QueryStudentPort
) {
    fun execute(): List<CompletedOrderResponse> =
        queryOrderPort.queryAllIsOrderedItem(OrderStatus.COMPLETED).map {
            val item = queryItemPort.queryItemById(it.itemId) ?: throw ItemNotFoundException()
            val student = queryStudentPort.queryStudentById(it.studentId) ?: throw StudentNotFoundException()
            val user = queryUserPort.queryUserById(student.user) ?: throw UserNotFoundException()

            CompletedOrderResponse(
                id = it.id,
                count = it.count,
                price = it.price,
                orderStatus = it.orderStatus,
                item = ItemResponse(
                    id = it.itemId,
                    name = item.name,
                    price = item.price
                ),
                user = UserResponse(
                    id = user.id,
                    email = user.email,
                    name = user.name,
                    profileImage = user.profileImage
                )
            )
        }
}