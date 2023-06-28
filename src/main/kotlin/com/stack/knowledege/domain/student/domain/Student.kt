package com.stack.knowledege.domain.student.domain

import com.stack.knowledege.global.annotation.Aggregate
import java.util.*

@Aggregate
data class Student(
    val id: UUID,
    val point: Int,
    val user: UUID?
)