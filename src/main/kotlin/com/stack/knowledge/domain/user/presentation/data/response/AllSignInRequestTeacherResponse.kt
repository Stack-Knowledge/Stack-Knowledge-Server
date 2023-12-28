package com.stack.knowledge.domain.user.presentation.data.response

import java.time.LocalDateTime

data class AllSignInRequestTeacherResponse(
    val name: String,
    val createdAt: LocalDateTime
)