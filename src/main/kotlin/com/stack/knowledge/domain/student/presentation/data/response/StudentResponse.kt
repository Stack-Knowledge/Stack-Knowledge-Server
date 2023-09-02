package com.stack.knowledge.domain.student.presentation.data.response

import java.util.*

class StudentResponse(
    val id: UUID,
    val currentPoint: Int,
    val cumulatePoint: Int
)