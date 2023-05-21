package com.stack.knowledege.domain.mission.presentation.data.response

import java.util.*

class MissionResponse(
    val id: UUID,
    val title: String,
    val content: String,
    val duration: Int,
    val timeLimit: Int,
    val isSolved: Boolean
)