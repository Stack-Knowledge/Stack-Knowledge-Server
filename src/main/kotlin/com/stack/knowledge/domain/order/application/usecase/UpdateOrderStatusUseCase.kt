package com.stack.knowledge.domain.order.application.usecase

import com.stack.knowledge.common.annotation.usecase.UseCase
import com.stack.knowledge.domain.item.application.spi.QueryItemPort
import com.stack.knowledge.domain.item.exception.ItemNotFoundException
import com.stack.knowledge.domain.order.application.spi.OrderPort
import com.stack.knowledge.domain.order.domain.constant.OrderStatus
import com.stack.knowledge.domain.order.exception.AlreadyCompletedOrderException
import com.stack.knowledge.domain.order.exception.LackOrderException
import com.stack.knowledge.domain.order.exception.OrderNotFoundException
import com.stack.knowledge.domain.order.presentation.data.request.UpdateOrderStatusRequest

@UseCase
class UpdateOrderStatusUseCase(
    private val orderPort: OrderPort,
    private val queryItemPort: QueryItemPort
) {
    fun execute(updateOrderStatusRequest: List<UpdateOrderStatusRequest>) {
        updateOrderStatusRequest.map {
            val order = orderPort.queryOrderById(it.orderId) ?: throw OrderNotFoundException()
            val item = queryItemPort.queryItemById(order.itemId) ?: throw ItemNotFoundException()

            if (order.orderStatus != OrderStatus.IS_ORDERED)
                AlreadyCompletedOrderException()

            if (order.count - it.count < 0 || order.price - (item.price * it.count) < 0) {
                throw LackOrderException()
            }

            val saveOrder = when {
                order.count > it.count -> order.copy(count = order.count - it.count, price = order.price - item.price * it.count)
                order.count == it.count -> order.copy(count = 0, price = 0, orderStatus = OrderStatus.COMPLETED)
                else -> throw LackOrderException()
            }

            orderPort.save(saveOrder)
        }
    }
}