package com.stack.knowledege.domain.order.presentation

import com.stack.knowledege.domain.order.application.usecase.OrderItemUseCase
import com.stack.knowledege.domain.order.application.usecase.ProvideItemUseCase
import com.stack.knowledege.domain.order.application.usecase.QueryIsOrderedOrderUseCase
import com.stack.knowledege.domain.order.presentation.data.request.OrderItemRequest
import com.stack.knowledege.domain.order.presentation.data.request.UpdateOrderStatusRequest
import com.stack.knowledege.domain.order.presentation.data.response.IsOrderedOrderResponse
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
    private val provideItemUseCase: ProvideItemUseCase
) {
    @PostMapping
    fun orderItem(@RequestBody @Valid orderItemRequest: OrderItemRequest): ResponseEntity<Void> =
        orderItemUseCase.execute(orderItemRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @GetMapping
    fun queryIsOrderedOrder(): ResponseEntity<List<IsOrderedOrderResponse>> =
        queryIsOrderedOrderUseCase.execute()
            .let { ResponseEntity.ok(it) }

    @PatchMapping("/{order_id}")
    fun updateOrderStatus(@PathVariable("order_id") orderId: UUID, @RequestBody @Valid updateOrderStatusRequest: UpdateOrderStatusRequest): ResponseEntity<Void> =
        provideItemUseCase.execute(orderId, updateOrderStatusRequest)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }
}