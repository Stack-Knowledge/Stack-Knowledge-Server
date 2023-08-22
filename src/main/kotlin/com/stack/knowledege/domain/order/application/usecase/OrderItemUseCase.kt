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
import com.stack.knowledege.domain.student.application.spi.StudentPort
import java.util.UUID

@UseCase
class OrderItemUseCase(
    private val queryItemPort: QueryItemPort,
    private val securityService: SecurityService,
    private val studentPort: StudentPort,
    private val commandOrderPort: CommandOrderPort
) {
    fun execute(orderItemRequest: OrderItemRequest) {
        val student = securityService.queryCurrentUser().let { studentPort.queryStudentByUser(it) ?: throw StudentNotFoundException() }

        queryItemPort.queryAllItem().map {
            val sum = (orderItemRequest.count * it.price).let { price ->
                student.currentPoint - price
            }

            if (sum < 0)
                throw LackPointException()

            studentPort.save(student.copy(currentPoint = sum))

            val order = Order(
                id = UUID.randomUUID(),
                count = orderItemRequest.count,
                price = sum,
                orderStatus = OrderStatus.IS_ORDERED,
                itemId = it.id,
                studentId = student.id
            )

            commandOrderPort.save(order)
        }
    }
}