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
import com.stack.knowledege.domain.order.application.spi.QueryOrderPort
import com.stack.knowledege.domain.student.application.spi.StudentPort
import java.util.UUID

@UseCase
class OrderItemUseCase(
    private val queryItemPort: QueryItemPort,
    private val securityService: SecurityService,
    private val studentPort: StudentPort,
    private val commandOrderPort: CommandOrderPort,
    private val queryOrderPort: QueryOrderPort
) {
    fun execute(orderItemRequest: List<OrderItemRequest>) {
        val student = securityService.queryCurrentUser().let {
            studentPort.queryStudentByUser(it) ?: throw StudentNotFoundException()
        }
        val orders = queryOrderPort.queryAllIsOrderedItem(OrderStatus.IS_ORDERED)

        orderItemRequest.map {
            val item = queryItemPort.queryItemById(it.itemId) ?: throw ItemNotFoundException()

            val price = it.count * item.price
            val sum = student.currentPoint - price

            if (sum < 0)
                throw LackPointException()

            studentPort.save(student.copy(currentPoint = sum))

            val existingOrder = orders.find { findOrder ->
                findOrder.itemId == it.itemId
            }

            val saveOrder: Order

            if (existingOrder == null)
                saveOrder = Order(
                    id = UUID.randomUUID(),
                    count = it.count,
                    price = price,
                    orderStatus = OrderStatus.IS_ORDERED,
                    itemId = it.itemId,
                    studentId = student.id
                )
            else
                saveOrder = existingOrder.copy(
                    count = existingOrder.count + it.count,
                    price = existingOrder.price + it.count * item.price
                )

            commandOrderPort.save(saveOrder)
        }
    }
}