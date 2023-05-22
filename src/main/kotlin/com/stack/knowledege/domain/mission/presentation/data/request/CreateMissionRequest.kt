package com.stack.knowledege.domain.mission.presentation.data.request

import java.util.UUID

class CreateMissionRequest(
    val id: UUID,
    val title: String,
    val content: String,
    val duration: Int,
    val timeLimit: Int,
    val isSolved: Boolean
)