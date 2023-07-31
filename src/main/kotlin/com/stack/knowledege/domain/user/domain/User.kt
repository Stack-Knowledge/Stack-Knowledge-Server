package com.stack.knowledege.domain.user.domain

import com.stack.knowledege.domain.user.domain.constant.Authority
import com.stack.knowledege.global.annotation.Aggregate
import java.util.*

@Aggregate
data class User(
    val id: UUID,
    val email: String,
    val name: String,
    val profileImage: String? = "",
    val authority: Authority
)