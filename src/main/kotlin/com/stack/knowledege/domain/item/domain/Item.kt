package com.stack.knowledege.domain.item.domain

import java.util.*

data class Item(
    val id: UUID,
    val name: String,
    val text: String,
    val price: Int
)