package com.stack.knowledege.domain.student.domain

import com.stack.knowledege.global.annotation.Aggregate
import java.util.*

@Aggregate
data class Student(
    val id: UUID,
    val currentPoint: Int,
    val cumulatePoint: Int,
    val user: UUID
)