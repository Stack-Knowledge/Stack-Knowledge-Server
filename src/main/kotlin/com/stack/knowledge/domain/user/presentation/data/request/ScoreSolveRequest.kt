package com.stack.knowledge.domain.user.presentation.data.request

import com.stack.knowledge.domain.solve.domain.constant.SolveStatus
import javax.persistence.EnumType
import javax.persistence.Enumerated

data class ScoreSolveRequest(
    @field:Enumerated(EnumType.STRING)
    val solveStatus: SolveStatus
)