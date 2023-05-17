package com.stack.knowledege.domain.item.application.usecase

import com.stack.knowledege.domain.item.application.spi.QueryItemPort
import com.stack.knowledege.domain.item.presentation.data.response.ItemResponse
import com.stack.knowledege.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryAllItemUseCase(
    private val queryItemPort: QueryItemPort
) {
    fun execute(): List<ItemResponse> =
        queryItemPort.queryAllItem()
            .map { ItemResponse(
                id = it.id,
                name = it.name,
                text = it.text,
                price = it.price
            ) }
}