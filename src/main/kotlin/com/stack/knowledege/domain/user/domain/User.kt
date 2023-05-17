package com.stack.knowledege.domain.user.domain

import com.stack.knowledege.domain.user.domain.constant.UserRole
import java.util.*

class User(
    val id: UUID,
    val email: String,
    val name: String,
    val grade: String,
    val number: String,
    val roles: MutableList<UserRole> = mutableListOf()
)