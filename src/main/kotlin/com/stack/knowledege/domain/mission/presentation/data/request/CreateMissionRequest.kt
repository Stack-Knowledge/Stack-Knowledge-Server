package com.stack.knowledege.domain.mission.presentation.data.request

class CreateMissionRequest(
    val title: String,
    val introduce: String,
    val content: String,
    val duration: Int,
    val timeLimit: Int,
    val isSolved: Boolean
)