package com.stack.knowledege.domain.order.application.usecase

import com.stack.knowledege.domain.item.application.spi.QueryItemPort
import com.stack.knowledege.domain.order.application.spi.CommandOrderPort
import com.stack.knowledege.domain.order.domain.Order
import com.stack.knowledege.domain.order.domain.constant.OrderStatus
import com.stack.knowledege.domain.order.exception.LackPointException
import com.stack.knowledege.domain.order.presentation.data.request.OrderItemRequest
import com.stack.knowledege.domain.student.exception.StudentNotFoundException
import com.stack.knowledege.common.annotation.usecase.UseCase
import com.stack.knowledege.common.service.SecurityService
import com.stack.knowledege.domain.item.exception.ItemNotFoundException
import com.stack.knowledege.domain.student.application.spi.StudentPort
import java.util.UUID

@UseCase
class OrderItemUseCase(
    private val queryItemPort: QueryItemPort,
    private val securityService: SecurityService,
    private val studentPort: StudentPort,
    private val commandOrderPort: CommandOrderPort
) {
    fun execute(orderItemRequest: List<OrderItemRequest>) {
        val student = securityService.queryCurrentUser().let {
            studentPort.queryStudentByUser(it) ?: throw StudentNotFoundException()
        }

        orderItemRequest.map {
            val item = queryItemPort.queryItemById(it.itemId) ?: throw ItemNotFoundException()

            val sum = (it.count * item.price).let { price ->
                student.currentPoint - price
            }

            if (sum < 0)
                throw LackPointException()

            studentPort.save(student.copy(currentPoint = sum))

            val order = Order(
                id = UUID.randomUUID(),
                count = it.count,
                price = it.count * item.price,
                orderStatus = OrderStatus.IS_ORDERED,
                itemId = it.itemId,
                studentId = student.id
            )

            commandOrderPort.save(order)
        }
    }
}