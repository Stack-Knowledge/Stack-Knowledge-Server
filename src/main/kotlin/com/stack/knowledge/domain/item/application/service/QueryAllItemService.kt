package com.stack.knowledge.domain.item.application.service

import com.stack.knowledge.common.annotation.usecase.ReadOnlyUseCase
import com.stack.knowledge.domain.item.application.spi.QueryItemPort
import com.stack.knowledge.domain.item.presentation.data.response.ItemResponse

@ReadOnlyUseCase
class QueryAllItemUseCase(
    private val queryItemPort: QueryItemPort
) {
    fun execute(): List<ItemResponse> =
        queryItemPort.queryAllItem().map {
            ItemResponse(
                itemId = it.id,
                name = it.name,
                price = it.price,
                image = it.image
            )
        }
}