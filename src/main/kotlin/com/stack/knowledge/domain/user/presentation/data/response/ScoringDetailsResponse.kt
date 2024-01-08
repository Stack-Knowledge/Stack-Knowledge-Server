package com.stack.knowledge.domain.user.presentation.data.response

import java.util.*

data class ScoringDetailsResponse(
    val solveId: UUID,
    val title: String,
    val solution: String
)