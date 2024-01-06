package com.stack.knowledge.domain.order.presentation

import com.stack.knowledge.domain.order.application.service.OrderItemService
import com.stack.knowledge.domain.order.application.service.QueryIsOrderedOrderService
import com.stack.knowledge.domain.order.application.service.UpdateOrderService
import com.stack.knowledge.domain.order.presentation.data.request.OrderItemRequest
import com.stack.knowledge.domain.order.presentation.data.request.UpdateOrderRequest
import com.stack.knowledge.domain.order.presentation.data.response.IsOrderedOrderResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/order")
class OrderWebAdapter(
    private val orderItemService: OrderItemService,
    private val queryIsOrderedOrderService: QueryIsOrderedOrderService,
    private val updateOrderService: UpdateOrderService
) {
    @PostMapping
    fun orderItem(@RequestBody @Valid orderItemRequest: List<OrderItemRequest>): ResponseEntity<Unit> =
        orderItemService.execute(orderItemRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @GetMapping
    fun queryIsOrderedOrder(): ResponseEntity<List<IsOrderedOrderResponse>> =
        queryIsOrderedOrderService.execute()
            .let { ResponseEntity.ok(it) }

    @PatchMapping
    fun updateOrder(@RequestBody @Valid updateOrderRequest: List<UpdateOrderRequest>): ResponseEntity<Unit> =
        updateOrderService.execute(updateOrderRequest)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }
}