package com.stack.knowledge.domain.user.presentation.data.response

import com.stack.knowledge.domain.solve.domain.constant.SolveStatus
import java.util.UUID

data class AllScoringResponse(
    val solveId: UUID,
    val solveStatus: SolveStatus,
    val title: String,
    val point: Int,
    val user: UserResponse
)