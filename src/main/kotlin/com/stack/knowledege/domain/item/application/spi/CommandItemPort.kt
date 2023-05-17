package com.stack.knowledege.domain.item.application.spi

import com.stack.knowledege.domain.item.domain.Item
import java.util.UUID

interface CommandItemPort {
    fun saveItem(item: Item): Item
    fun deleteItem(itemId: UUID)
}