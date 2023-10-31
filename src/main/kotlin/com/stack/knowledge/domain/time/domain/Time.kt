package com.stack.knowledge.domain.time.domain

import com.stack.knowledge.domain.mission.domain.Mission
import java.time.LocalDateTime
import java.util.UUID

data class Time(
    val id: UUID,
    val mission: Mission,
    val createdAt: LocalDateTime
)