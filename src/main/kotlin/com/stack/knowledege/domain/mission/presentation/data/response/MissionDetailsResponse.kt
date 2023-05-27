package com.stack.knowledege.domain.mission.presentation.data.response

import com.stack.knowledege.domain.user.presentation.data.response.UserResponse
import java.util.UUID

class MissionDetailsResponse(
    val title: String,
    val content: String,
    val timeLimit: Int,
    val user: UserResponse
)