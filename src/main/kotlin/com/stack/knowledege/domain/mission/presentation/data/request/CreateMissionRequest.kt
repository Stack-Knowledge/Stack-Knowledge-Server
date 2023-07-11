package com.stack.knowledege.domain.mission.presentation.data.request

import java.time.LocalDateTime

data class CreateMissionRequest(
    val title: String,
    val introduce: String,
    val duration: LocalDateTime,
    val content: String,
    val timeLimit: Int
)