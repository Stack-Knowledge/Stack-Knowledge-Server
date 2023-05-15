package com.stack.knowledege.domain.user.domain

import com.stack.knowledege.domain.user.domain.constant.UserRole
import java.util.*

data class User(
    val id: UUID,
    val email: String,
    val name: String,
    val grade: Int,
    val number: Int,
    val roles: MutableList<UserRole> = mutableListOf()
)