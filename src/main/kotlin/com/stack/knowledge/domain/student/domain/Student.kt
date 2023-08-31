package com.stack.knowledge.domain.student.domain

import com.stack.knowledge.common.annotation.Aggregate
import java.util.*

@Aggregate
data class Student(
    val id: UUID,
    val currentPoint: Int,
    val cumulatePoint: Int,
    val user: UUID
)