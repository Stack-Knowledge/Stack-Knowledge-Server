package com.stack.knowledege.domain.user.presentation.data.response

import java.util.*

class UserResponse(
    val id: UUID,
    val email: String,
    val name: String,
    val point: Int,
    val profileImage: String?
)