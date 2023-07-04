package com.stack.knowledege.domain.mission.presentation.data.request

import java.util.UUID

data class SolveMissionRequest(
    val solvation: String,
    val userId: UUID
)