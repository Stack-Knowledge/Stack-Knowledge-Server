package com.stack.knowledge.domain.user.presentation.data.response

import java.util.UUID

data class UserResponse(
    val id: UUID,
    val email: String,
    val name: String,
    val profileImage: String?
)