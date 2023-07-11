package com.stack.knowledege.domain.user.presentation.data.response

import com.stack.knowledege.domain.solve.domain.constant.SolveStatus
import java.util.UUID

data class AllScoringResponse(
    val isSolved: Boolean,
    val solveStatus: SolveStatus,
    val student: UUID
)