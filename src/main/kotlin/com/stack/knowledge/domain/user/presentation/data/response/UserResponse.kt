package com.stack.knowledge.domain.user.presentation.data.response

import java.util.*

data class UserResponse(
    val id: UUID,
    val name: String,
    val profileImage: String?
)