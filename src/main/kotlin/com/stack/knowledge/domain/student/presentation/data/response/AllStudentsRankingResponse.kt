package com.stack.knowledge.domain.student.presentation.data.response

import com.stack.knowledge.domain.user.presentation.data.response.UserResponse
import java.util.UUID

data class AllStudentsRankingResponse(
    val id: UUID,
    val cumulatePoint: Int,
    val user: UserResponse
)