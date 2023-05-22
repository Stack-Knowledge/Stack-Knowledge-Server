package com.stack.knowledege.domain.user.presentation.data.response

import java.util.UUID

class UserResponse(
    val id: UUID,
    val name: String,
    val grade: Int,
    val number: Int
)