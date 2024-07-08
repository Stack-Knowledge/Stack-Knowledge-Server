package com.stack.knowledge.domain.student.presentation.data.response

import com.stack.knowledge.domain.user.presentation.data.response.UserResponse
import java.util.*

data class StudentPointResponse(
    val id: UUID,
    val currentPoint: Int,
    val cumulatePoint: Int,
    val ranking: Int,
    val user: UserResponse
)