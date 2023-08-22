package com.stack.knowledege.domain.mission.presentation.data.response

import com.stack.knowledege.domain.mission.domain.constant.MissionStatus
import com.stack.knowledege.domain.user.presentation.data.response.UserResponse
import java.util.*

data class MissionResponse(
    val id: UUID,
    val title: String,
    val point: Int,
    val missionStatus: MissionStatus,
    val user: UserResponse
)