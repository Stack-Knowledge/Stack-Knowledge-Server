package com.stack.knowledge.domain.order.application.usecase

import com.stack.knowledge.domain.item.application.spi.QueryItemPort
import com.stack.knowledge.domain.item.exception.ItemNotFoundException
import com.stack.knowledge.domain.order.application.spi.CommandOrderPort
import com.stack.knowledge.domain.order.domain.Order
import com.stack.knowledge.domain.order.domain.constant.OrderStatus
import com.stack.knowledge.domain.order.exception.LackPointException
import com.stack.knowledge.domain.order.presentation.data.request.OrderItemRequest
import com.stack.knowledge.domain.student.application.spi.QueryStudentPort
import com.stack.knowledge.domain.student.exception.StudentNotFoundException
import com.stack.knowledge.common.annotation.usecase.UseCase
import com.stack.knowledge.common.service.SecurityService
import java.util.UUID

@UseCase
class OrderItemUseCase(
    private val queryItemPort: QueryItemPort,
    private val securityService: SecurityService,
    private val queryStudentPort: QueryStudentPort,
    private val commandOrderPort: CommandOrderPort
) {
    fun execute(itemId: UUID, orderItemRequest: OrderItemRequest) {
        val student = securityService.queryCurrentUser().let { queryStudentPort.queryStudentByUser(it) ?: throw StudentNotFoundException() }
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