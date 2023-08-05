package com.stack.knowledege.domain.mission.presentation.data.request

import javax.validation.constraints.NotBlank

data class CreateMissionRequest(
    @field:NotBlank
    val title: String,
    @field:NotBlank
    val content: String,
    val timeLimit: Int
)