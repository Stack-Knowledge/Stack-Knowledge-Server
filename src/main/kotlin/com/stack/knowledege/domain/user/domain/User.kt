package com.stack.knowledege.domain.user.domain

import com.stack.knowledege.domain.user.domain.constant.UserRole
import com.stack.knowledege.global.annotation.Aggregate
import java.util.*

@Aggregate
class User(
    val id: UUID,
    val email: String,
    val name: String,
    val grade: Int,
    val number: Int,
    val roles: MutableList<UserRole> = mutableListOf()
)