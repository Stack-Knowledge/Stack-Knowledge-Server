package com.stack.knowledge.domain.item.application.service

import com.stack.knowledge.common.annotation.service.ServiceWithReadOnlyTransaction
import com.stack.knowledge.domain.item.application.spi.QueryItemPort
import com.stack.knowledge.domain.item.presentation.data.response.ItemResponse
import org.springframework.cache.annotation.Cacheable

@ServiceWithReadOnlyTransaction
class QueryAllItemUseCase(
    private val queryItemPort: QueryItemPort
) {
    @Cacheable(
        value = ["ItemList"],
        cacheManager = "contentCacheManager",
    )
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