package com.stack.knowledege.domain.teacher.domain

import com.stack.knowledege.domain.user.domain.constant.UserRole
import com.stack.knowledege.global.annotation.Aggregate
import java.util.*

@Aggregate
class Teacher(
    val id: UUID,
    val email: String,
    val name: String,
    val roles: MutableList<UserRole> = mutableListOf(),
    val subject: String
)