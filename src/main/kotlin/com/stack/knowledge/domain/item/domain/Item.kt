package com.stack.knowledge.domain.item.domain

import com.stack.knowledge.common.annotation.Aggregate
import java.util.*

@Aggregate
data class Item(
    val id: UUID,
    val name: String,
    val price: Int
)