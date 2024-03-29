package com.stack.knowledge.domain.order.application.service

import com.stack.knowledge.common.annotation.service.ServiceWithTransaction
import com.stack.knowledge.common.service.SecurityService
import com.stack.knowledge.domain.item.application.spi.QueryItemPort
import com.stack.knowledge.domain.item.exception.ItemNotFoundException
import com.stack.knowledge.domain.order.application.spi.CommandOrderPort
import com.stack.knowledge.domain.order.application.spi.QueryOrderPort
import com.stack.knowledge.domain.order.domain.Order
import com.stack.knowledge.domain.order.exception.LackPointException
import com.stack.knowledge.domain.order.presentation.data.request.OrderItemRequest
import com.stack.knowledge.domain.student.application.spi.StudentPort
import com.stack.knowledge.domain.student.exception.StudentNotFoundException
import java.util.*

@ServiceWithTransaction
class OrderItemService(
    private val queryItemPort: QueryItemPort,
    private val securityService: SecurityService,
    private val studentPort: StudentPort,
    private val commandOrderPort: CommandOrderPort,
    private val queryOrderPort: QueryOrderPort
) {
    fun execute(orderItemRequest: List<OrderItemRequest>) {
        val student = securityService.queryCurrentUserId().let {
            studentPort.queryStudentById(it) ?: throw StudentNotFoundException()
        }

        orderItemRequest.forEach {
            val item = queryItemPort.queryItemById(it.itemId) ?: throw ItemNotFoundException()

            val price = it.count * item.price
            val sum = student.currentPoint - price

            if (sum < 0)
                throw LackPointException()

            studentPort.save(student.copy(currentPoint = sum))

            val orders = queryOrderPort.queryAllByStudent(student)
            val existingOrder = orders.find { order ->
                order.itemId == it.itemId
            }

            val saveOrder = existingOrder?.let { order ->
                order.copy(
                    count = order.count + it.count,
                    price = order.price + price
                )
            } ?: Order(
                id = UUID(0, 0),
                count = it.count,
                price = price,
                itemId = it.itemId,
                studentId = student.id
            )

            commandOrderPort.save(saveOrder)
        }
    }
}