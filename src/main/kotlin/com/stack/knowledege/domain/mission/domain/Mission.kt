package com.stack.knowledege.domain.mission.domain

import com.stack.knowledege.domain.user.presentation.data.response.UserResponse
import com.stack.knowledege.global.annotation.Aggregate
import java.util.UUID

@Aggregate
class Mission(
    val id: UUID,
    val title: String,
    val introduce: String,
    val content: String,
    val timeLimit: Int,
    val isSolved: Boolean,
    val user: UserResponse
)