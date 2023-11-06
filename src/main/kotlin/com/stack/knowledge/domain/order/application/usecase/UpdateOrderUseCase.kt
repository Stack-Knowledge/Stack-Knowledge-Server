package com.stack.knowledge.domain.order.application.usecase

import com.stack.knowledge.common.annotation.usecase.UseCase
import com.stack.knowledge.domain.item.application.spi.QueryItemPort
import com.stack.knowledge.domain.item.exception.ItemNotFoundException
import com.stack.knowledge.domain.order.application.spi.OrderPort
import com.stack.knowledge.domain.order.exception.LackOrderException
import com.stack.knowledge.domain.order.exception.OrderNotFoundException
import com.stack.knowledge.domain.order.presentation.data.request.UpdateOrderRequest

@UseCase
class UpdateOrderUseCase(
    private val orderPort: OrderPort,
    private val queryItemPort: QueryItemPort
) {
    fun execute(updateOrderRequest: List<UpdateOrderRequest>) {
        updateOrderRequest.map {
            val order = orderPort.queryOrderById(it.orderId) ?: throw OrderNotFoundException()
            val item = queryItemPort.queryItemById(order.itemId) ?: throw ItemNotFoundException()

            val count = order.count - it.count
            val price = order.price - item.price * it.count

            if (count < 0 || price < 0)
                throw LackOrderException()

            when {
                count > 0 -> orderPort.save(order.copy(count = count, price = price))
                count == 0 -> orderPort.delete(order)
                else -> throw LackOrderException()
            }
        }
    }
}