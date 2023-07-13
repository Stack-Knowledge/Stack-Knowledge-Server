package com.stack.knowledege.domain.mission.presentation.data.request

data class CreateMissionRequest(
    val title: String,
    val introduce: String,
    val content: String,
    val timeLimit: Int
)