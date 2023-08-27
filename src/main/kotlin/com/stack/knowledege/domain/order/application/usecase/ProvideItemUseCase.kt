package com.stack.knowledege.domain.order.application.usecase

import com.stack.knowledege.common.annotation.usecase.UseCase
import com.stack.knowledege.domain.order.application.spi.OrderPort
import com.stack.knowledege.domain.order.domain.constant.OrderStatus
import com.stack.knowledege.domain.order.exception.AlreadyCompletedOrderException
import com.stack.knowledege.domain.order.exception.LackOrderException
import com.stack.knowledege.domain.order.exception.OrderNotFoundException
import com.stack.knowledege.domain.order.presentation.data.request.UpdateOrderStatusRequest
import java.util.UUID

@UseCase
class ProvideItemUseCase(
    private val orderPort: OrderPort
) {
    fun execute(orderId: UUID, updateOrderStatusRequest: UpdateOrderStatusRequest) {
        val order = orderPort.queryOrderById(orderId) ?: throw OrderNotFoundException()

        if (order.orderStatus == OrderStatus.COMPLETED)
            AlreadyCompletedOrderException()

        if (order.count - updateOrderStatusRequest.count < 0)
            LackOrderException()
        else if (order.count - updateOrderStatusRequest.count >= 0) {
            orderPort.save(order.copy(count = order.count - updateOrderStatusRequest.count, orderStatus = OrderStatus.COMPLETED))
        }
    }
}