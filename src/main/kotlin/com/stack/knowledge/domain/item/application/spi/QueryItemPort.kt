package com.stack.knowledge.domain.item.application.spi

import com.stack.knowledge.domain.item.domain.Item
import java.util.*

interface QueryItemPort {
    fun queryAllItem(): List<Item>
    fun queryItemById(itemId: UUID): Item?
}