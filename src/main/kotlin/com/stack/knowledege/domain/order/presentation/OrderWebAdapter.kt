package com.stack.knowledege.domain.order.presentation

import com.stack.knowledege.domain.order.application.usecase.OrderItemUseCase
import com.stack.knowledege.domain.order.presentation.data.request.OrderItemRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/order")
class OrderWebAdapter(
    private val orderItemUseCase: OrderItemUseCase
) {
    @PostMapping("/{item_id}")
    fun execute(@PathVariable("item_id") itemId: UUID, @RequestBody orderItemRequest: OrderItemRequest): ResponseEntity<Void> =
        orderItemUseCase.execute(itemId, orderItemRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }
}