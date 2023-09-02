package com.stack.knowledge.domain.user.domain

import com.stack.knowledge.domain.user.domain.constant.Authority
import com.stack.knowledge.common.annotation.Aggregate
import java.util.*

@Aggregate
data class User(
    val id: UUID,
    val email: String,
    val name: String,
    val profileImage: String? = "",
    val authority: Authority
)