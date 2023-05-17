package com.stack.knowledege.domain.item.application.spi

import com.stack.knowledege.domain.item.domain.Item
import java.util.UUID

interface QueryItemPort {
    fun queryAllItem(): List<Item>
    fun queryItemById(itemId: UUID): Item
}