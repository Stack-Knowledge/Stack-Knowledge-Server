package com.stack.knowledege.domain.item.presentation

import com.stack.knowledege.domain.item.application.usecase.QueryAllItemUseCase
import com.stack.knowledege.domain.item.application.usecase.QueryItemByIdUseCase
import com.stack.knowledege.domain.item.presentation.data.response.ItemResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/item")
class ItemWebAdapter(
    private val queryAllItemUseCase: QueryAllItemUseCase,
    private val queryItemByIdUseCase: QueryItemByIdUseCase
) {
    @GetMapping
    fun queryAllItem(): ResponseEntity<List<ItemResponse>> =
        queryAllItemUseCase.execute()
            .let { ResponseEntity.ok(it) }

    @GetMapping("/{item_id}")
    fun queryItemById(@PathVariable("item_id") itemId: UUID): ResponseEntity<ItemResponse> =
        queryItemByIdUseCase.execute(itemId)
            .let { ResponseEntity.ok(it) }
}