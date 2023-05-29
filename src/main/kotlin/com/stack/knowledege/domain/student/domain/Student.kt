package com.stack.knowledege.domain.student.domain

import com.stack.knowledege.domain.user.domain.constant.UserRole
import com.stack.knowledege.global.annotation.Aggregate
import java.util.*

@Aggregate
class Student(
    val id: UUID,
    val email: String,
    val name: String,
    val roles: MutableList<UserRole> = mutableListOf(),
    val grade: Int,
    val classes: Int,
    val number: Int,
    val point: Int
)