package com.stack.knowledge.domain.item.presentation

import com.stack.knowledge.domain.item.application.usecase.QueryAllItemUseCase
import com.stack.knowledge.domain.item.presentation.data.response.ItemResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/item")
class ItemWebAdapter(
    private val queryAllItemUseCase: QueryAllItemUseCase
) {
    @GetMapping
    fun queryAllItem(): ResponseEntity<List<ItemResponse>> =
        queryAllItemUseCase.execute()
            .let { ResponseEntity.ok(it) }
}