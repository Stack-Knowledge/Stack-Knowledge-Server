package com.stack.knowledege.domain.mission.presentation.data.request

import java.util.UUID

data class SolveMissionRequest(
    val answer: String,
    val userId: UUID
)