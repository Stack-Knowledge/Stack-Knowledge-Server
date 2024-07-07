package com.stack.knowledge.domain.user.presentation.data.response

import java.util.*

data class UserResponse(
    val userId: UUID,
    val name: String,
    val profileImage: String?
)