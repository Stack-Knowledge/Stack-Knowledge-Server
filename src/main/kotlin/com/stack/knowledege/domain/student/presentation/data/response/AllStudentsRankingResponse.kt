package com.stack.knowledege.domain.student.presentation.data.response

import com.stack.knowledege.domain.user.presentation.data.response.UserResponse
import java.util.UUID

data class AllStudentsRankingResponse(
    val id: UUID,
    val point: Int,
    val user: UserResponse
)