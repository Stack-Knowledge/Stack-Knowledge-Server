package com.stack.knowledege.domain.item.domain

import com.stack.knowledege.global.annotation.Aggregate
import java.util.*

@Aggregate
data class Item(
    val id: UUID,
    val name: String,
    val text: String,
    val price: Int
)