package com.stack.knowledge.domain.order.application.usecase

import com.stack.knowledge.common.annotation.usecase.UseCase
import com.stack.knowledge.domain.order.application.spi.OrderPort
import com.stack.knowledge.domain.order.domain.constant.OrderStatus
import com.stack.knowledge.domain.order.exception.AlreadyCompletedOrderException
import com.stack.knowledge.domain.order.exception.OrderNotFoundException
import com.stack.knowledge.domain.order.presentation.data.request.UpdateOrderStatusRequest
import java.util.UUID

@UseCase
class UpdateOrderStatusUseCase(
    private val orderPort: OrderPort
) {
    fun execute(orderId: UUID, updateOrderStatusRequest: UpdateOrderStatusRequest) {
        val order = orderPort.queryOrderById(orderId) ?: throw OrderNotFoundException()

        if (order.orderStatus == OrderStatus.COMPLETED)
            throw AlreadyCompletedOrderException()

        orderPort.save(order.copy(orderStatus = updateOrderStatusRequest.orderStatus))
    }
}