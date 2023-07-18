package com.stack.knowledege.domain.mission.presentation.data.request

import javax.validation.constraints.NotBlank

data class SolveMissionRequest(
    @field:NotBlank
    val solvation: String
)