package com.stack.knowledege.domain.solve.domain

import com.stack.knowledege.domain.solve.domain.constant.SolveStatus
import com.stack.knowledege.domain.common.annotation.Aggregate
import java.util.*

@Aggregate
data class Solve(
    val id: UUID,
    val solvation: String,
    val solveStatus: SolveStatus,
    val student: UUID,
    val mission: UUID
)