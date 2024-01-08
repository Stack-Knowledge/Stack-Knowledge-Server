package com.stack.knowledge.domain.solve.domain

import com.stack.knowledge.common.annotation.Aggregate
import com.stack.knowledge.domain.solve.domain.constant.SolveStatus
import java.util.*

@Aggregate
data class Solve(
    val id: UUID,
    val solution: String,
    val solveStatus: SolveStatus,
    val student: UUID,
    val mission: UUID
)