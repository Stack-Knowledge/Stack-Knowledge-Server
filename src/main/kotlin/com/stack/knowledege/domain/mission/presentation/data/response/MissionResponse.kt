package com.stack.knowledege.domain.mission.presentation.data.response

import java.util.*

class MissionResponse(
    val id: UUID,
    val title: String,
    val introduce: String,
    val content: String,
    val timeLimit: Int,
    val isSolved: Boolean
)