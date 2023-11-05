package com.stack.knowledge.domain.order.application.usecase

import com.stack.knowledge.common.annotation.usecase.ReadOnlyUseCase
import com.stack.knowledge.domain.item.application.spi.QueryItemPort
import com.stack.knowledge.domain.item.exception.ItemNotFoundException
import com.stack.knowledge.domain.item.presentation.data.response.ItemResponse
import com.stack.knowledge.domain.order.application.spi.QueryOrderPort
import com.stack.knowledge.domain.order.presentation.data.response.IsOrderedOrderResponse
import com.stack.knowledge.domain.student.application.spi.QueryStudentPort
import com.stack.knowledge.domain.student.exception.StudentNotFoundException
import com.stack.knowledge.domain.user.application.spi.QueryUserPort
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import com.stack.knowledge.domain.user.presentation.data.response.UserResponse

@ReadOnlyUseCase
class QueryIsOrderedOrderUseCase(
    private val queryOrderPort: QueryOrderPort,
    private val queryItemPort: QueryItemPort,
    private val queryUserPort: QueryUserPort,
    private val queryStudentPort: QueryStudentPort
) {
    fun execute(): List<IsOrderedOrderResponse> =
        queryOrderPort.queryAllOrderByCreatedAtDesc().map {
            val item = queryItemPort.queryItemById(it.itemId) ?: throw ItemNotFoundException()
            val student = queryStudentPort.queryStudentById(it.studentId) ?: throw StudentNotFoundException()
            val user = queryUserPort.queryUserById(student.user) ?: throw UserNotFoundException()

            IsOrderedOrderResponse(
                id = it.id,
                count = it.count,
                price = it.price,
                item = ItemResponse(
                    itemId = it.itemId,
                    name = item.name,
                    price = item.price,
                    image = item.image
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