package com.stack.knowledege.domain.mission.domain

import com.stack.knowledege.global.annotation.Aggregate
import java.time.LocalDateTime
import java.util.UUID

@Aggregate
data class Mission(
    val id: UUID,
    val title: String,
    val introduce: String,
    val content: String,
    val timeLimit: Int,
    val userId: UUID
)