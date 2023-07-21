package com.stack.knowledege.domain.item.application.spi

import com.stack.knowledege.domain.item.domain.Item

interface QueryItemPort {
    fun queryAllItem(): List<Item>
}