package com.stack.knowledge.domain.order.presentation

import com.stack.knowledge.domain.order.application.usecase.OrderItemUseCase
import com.stack.knowledge.domain.order.application.usecase.UpdateOrderUseCase
import com.stack.knowledge.domain.order.application.usecase.QueryIsOrderedOrderUseCase
import com.stack.knowledge.domain.order.presentation.data.request.OrderItemRequest
import com.stack.knowledge.domain.order.presentation.data.request.UpdateOrderRequest
import com.stack.knowledge.domain.order.presentation.data.response.IsOrderedOrderResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/order")
class OrderWebAdapter(
    private val orderItemUseCase: OrderItemUseCase,
    private val queryIsOrderedOrderUseCase: QueryIsOrderedOrderUseCase,
    private val updateOrderUseCase: UpdateOrderUseCase
) {
    @PostMapping
    fun orderItem(@RequestBody @Valid orderItemRequest: List<OrderItemRequest>): ResponseEntity<Void> =
        orderItemUseCase.execute(orderItemRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @GetMapping
    fun queryIsOrderedOrder(): ResponseEntity<List<IsOrderedOrderResponse>> =
        queryIsOrderedOrderUseCase.execute()
            .let { ResponseEntity.ok(it) }

    @PatchMapping
    fun updateOrder(@RequestBody @Valid updateOrderRequest: List<UpdateOrderRequest>): ResponseEntity<Void> =
        updateOrderUseCase.execute(updateOrderRequest)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }
}