package com.stack.knowledge.domain.mission.presentation.data.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CreateMissionRequest(
    @field:NotBlank
    @field:Size(max = 50)
    val title: String,
    @field:NotBlank
    @field:Size(max = 500)
    val content: String,
    @field:NotNull
    val timeLimit: Int
)