package com.stack.knowledege.domain.order.application.usecase

import com.stack.knowledege.domain.item.application.spi.QueryItemPort
import com.stack.knowledege.domain.item.exception.ItemNotFoundException
import com.stack.knowledege.domain.order.application.spi.CommandOrderPort
import com.stack.knowledege.domain.order.domain.Order
import com.stack.knowledege.domain.order.domain.constant.OrderStatus
import com.stack.knowledege.domain.order.exception.LackPointException
import com.stack.knowledege.domain.order.presentation.data.request.OrderItemRequest
import com.stack.knowledege.domain.student.application.spi.QueryStudentPort
import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class OrderItemUseCase(
    private val queryItemPort: QueryItemPort,
    private val queryUserPort: QueryUserPort,
    private val queryStudentPort: QueryStudentPort,
    private val commandOrderPort: CommandOrderPort
) {
    fun execute(itemId: UUID, orderItemRequest: OrderItemRequest) {
        val student = queryUserPort.queryCurrentUser().let { queryStudentPort.queryStudentByUser(it) }
        val item = queryItemPort.queryItemById(itemId) ?: throw ItemNotFoundException()

        val price = orderItemRequest.count * item.price
        val sum = student.currentPoint - price

        if (sum < 0)
            throw LackPointException()

        student.copy(currentPoint = sum)

        val order = Order(
            id = UUID.randomUUID(),
            count = orderItemRequest.count,
            price = sum,
            orderStatus = OrderStatus.IS_ORDERED,
            itemId = itemId,
            studentId = student.id
        )

        commandOrderPort.save(order)
    }
}