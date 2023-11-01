package com.stack.knowledge.domain.time.domain

import java.time.LocalDateTime
import java.util.*

data class Time(
    val id: UUID,
    val mission: UUID,
    val createdAt: LocalDateTime
)