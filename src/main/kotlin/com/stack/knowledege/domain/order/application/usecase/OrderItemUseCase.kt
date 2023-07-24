package com.stack.knowledege.domain.order.application.usecase

import com.stack.knowledege.domain.item.application.spi.QueryItemPort
import com.stack.knowledege.domain.item.exception.ItemNotFoundException
import com.stack.knowledege.domain.order.presentation.data.request.OrderItemRequest
import com.stack.knowledege.domain.student.application.spi.QueryStudentPort
import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class OrderItemUseCase(
    private val queryItemPort: QueryItemPort,
    private val queryUserPort: QueryUserPort,
    private val queryStudentPort: QueryStudentPort
) {
    fun execute(itemId: UUID, orderItemRequest: OrderItemRequest) {
        val student = queryUserPort.queryCurrentUser().let { queryStudentPort.queryStudentByUser(it) }
        val item = queryItemPort.queryItemById(itemId) ?: throw ItemNotFoundException()

        val price = orderItemRequest.count * item.price

        student.copy(currentPoint = student.currentPoint - price)
    }
}