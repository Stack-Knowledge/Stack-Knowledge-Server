package com.stack.knowledge.domain.mission.presentation.data.response

import com.stack.knowledge.domain.mission.domain.constant.MissionStatus
import com.stack.knowledge.domain.user.presentation.data.response.UserResponse
import java.util.*

data class MissionResponse(
    val id: UUID,
    val title: String,
    val point: Int,
    val missionStatus: MissionStatus,
    val user: UserResponse
)