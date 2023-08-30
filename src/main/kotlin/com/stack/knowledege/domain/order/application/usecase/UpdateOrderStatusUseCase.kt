package com.stack.knowledege.domain.order.application.usecase

import com.stack.knowledege.common.annotation.usecase.UseCase
import com.stack.knowledege.domain.item.application.spi.QueryItemPort
import com.stack.knowledege.domain.item.exception.ItemNotFoundException
import com.stack.knowledege.domain.order.application.spi.OrderPort
import com.stack.knowledege.domain.order.domain.constant.OrderStatus
import com.stack.knowledege.domain.order.exception.AlreadyCompletedOrderException
import com.stack.knowledege.domain.order.exception.LackOrderException
import com.stack.knowledege.domain.order.exception.OrderNotFoundException
import com.stack.knowledege.domain.order.presentation.data.request.UpdateOrderStatusRequest

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

            if (it.count - order.count < 0 || order.price - item.price * order.count < 0)
                throw LackOrderException()

            val saveOrder = when {
                order.count > it.count -> order.copy(count = it.count - order.count, price = order.price - item.price * order.count)
                order.count == it.count -> order.copy(count = 0, price = 0, orderStatus = OrderStatus.COMPLETED)
                else -> throw LackOrderException()
            }

            orderPort.save(saveOrder)
        }
    }
}