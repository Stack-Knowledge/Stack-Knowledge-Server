package com.stack.knowledge.domain.solve.presentation.data.request

import javax.validation.constraints.NotNull

data class SolveMissionRequest(
    @NotNull
    val solvation: String
)