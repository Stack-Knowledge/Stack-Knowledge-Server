package com.stack.knowledege.domain.item.application.usecase

import com.stack.knowledege.domain.item.application.spi.QueryItemPort
import com.stack.knowledege.domain.item.exception.ItemNotFoundException
import com.stack.knowledege.domain.item.presentation.data.response.ItemResponse
import com.stack.knowledege.global.annotation.usecase.ReadOnlyUseCase
import java.util.UUID

@ReadOnlyUseCase
class QueryItemByIdUseCase(
    private val queryItemPort: QueryItemPort
) {
    fun execute(id: UUID): ItemResponse {
        val item = queryItemPort.queryItemById(id) ?: throw ItemNotFoundException()
        return ItemResponse(
            itemId = item.id,
            name = item.name,
            text = item.text,
            price = item.price
        )
    }
}