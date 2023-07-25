package com.stack.knowledege.domain.user.presentation.data.request

import com.stack.knowledege.domain.solve.domain.constant.SolveStatus
import javax.persistence.EnumType
import javax.persistence.Enumerated

class ScoreSolveRequest(
    @field:Enumerated(EnumType.STRING)
    val solveStatus: SolveStatus
)