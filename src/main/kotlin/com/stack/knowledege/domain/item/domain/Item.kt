package com.stack.knowledege.domain.item.domain

import com.stack.knowledege.common.annotation.Aggregate
import java.util.*

@Aggregate
data class Item(
    val id: UUID,
    val name: String,
    val price: Int
)