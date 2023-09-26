package com.stack.knowledge.domain.item.application.usecase

import com.stack.knowledge.domain.item.application.spi.QueryItemPort
import com.stack.knowledge.domain.item.presentation.data.response.ItemResponse
import com.stack.knowledge.common.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryAllItemUseCase(
    private val queryItemPort: QueryItemPort
) {
//    @Cacheable(cacheNames = ["items"], key = "'all'", sync = true)
    fun execute(): List<ItemResponse> =
        queryItemPort.queryAllItem()
            .map {
                ItemResponse(
                    itemId = it.id,
                    name = it.name,
                    price = it.price,
                    image = it.image
                )
            }
}