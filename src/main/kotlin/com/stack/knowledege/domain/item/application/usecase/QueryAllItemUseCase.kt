package com.stack.knowledege.domain.item.application.usecase

import com.stack.knowledege.domain.item.application.spi.QueryItemPort
import com.stack.knowledege.domain.item.presentation.data.response.ItemResponse
import com.stack.knowledege.common.annotation.usecase.ReadOnlyUseCase
import org.springframework.cache.annotation.Cacheable

@ReadOnlyUseCase
class QueryAllItemUseCase(
    private val queryItemPort: QueryItemPort
) {
    @Cacheable(cacheNames = ["missions"])
    fun execute(): List<ItemResponse> =
        queryItemPort.queryAllItem()
            .map {
                ItemResponse(
                    id = it.id,
                    name = it.name,
                    price = it.price
                )
            }
}