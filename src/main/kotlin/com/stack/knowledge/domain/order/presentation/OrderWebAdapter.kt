package com.stack.knowledge.domain.order.presentation

import com.stack.knowledge.domain.order.application.usecase.OrderItemUseCase
import com.stack.knowledge.domain.order.application.usecase.UpdateOrderStatusUseCase
import com.stack.knowledge.domain.order.application.usecase.QueryIsOrderedOrderUseCase
import com.stack.knowledge.domain.order.presentation.data.request.OrderItemRequest
import com.stack.knowledge.domain.order.presentation.data.request.UpdateOrderStatusRequest
import com.stack.knowledge.domain.order.presentation.data.response.IsOrderedOrderResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/order")
class OrderWebAdapter(
    private val orderItemUseCase: OrderItemUseCase,
    private val queryIsOrderedOrderUseCase: QueryIsOrderedOrderUseCase,
    private val updateOrderStatusUseCase: UpdateOrderStatusUseCase
) {
    @PostMapping("/{item_id}")
    fun orderItem(@PathVariable("item_id") itemId: UUID, @RequestBody @Valid orderItemRequest: OrderItemRequest): ResponseEntity<Void> =
        orderItemUseCase.execute(itemId, orderItemRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @GetMapping
    fun queryIsOrderedOrder(): ResponseEntity<List<IsOrderedOrderResponse>> =
        queryIsOrderedOrderUseCase.execute()
            .let { ResponseEntity.ok(it) }

    @PatchMapping("/{order_id}")
    fun updateOrderStatus(@PathVariable("order_id") orderId: UUID, @RequestBody @Valid updateOrderStatusRequest: UpdateOrderStatusRequest): ResponseEntity<Void> =
        updateOrderStatusUseCase.execute(orderId, updateOrderStatusRequest)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }
}