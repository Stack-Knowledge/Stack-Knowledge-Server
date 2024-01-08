package com.stack.knowledge.domain.user.presentation.data.response

import java.time.LocalDateTime
import java.util.*

data class AllSignUpRequestTeacherResponse(
    val userId: UUID,
    val name: String,
    val createdAt: LocalDateTime
)