package com.stack.knowledge.domain.solve.presentation.data.request

import javax.validation.constraints.NotBlank

data class SolveMissionRequest(
    @field:NotBlank
    val solvation: String
)