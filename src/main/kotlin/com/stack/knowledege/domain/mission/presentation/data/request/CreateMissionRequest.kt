package com.stack.knowledege.domain.mission.presentation.data.request

import javax.validation.constraints.NotBlank

data class CreateMissionRequest(
    @field:NotBlank
    val title: String,
    @field:NotBlank
    val content: String,
    @field:NotBlank
    val timeLimit: Int,
    @field:NotBlank
    val point: Int
)