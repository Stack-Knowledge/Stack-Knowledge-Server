package com.stack.knowledge.domain.mission.domain

import com.stack.knowledge.common.annotation.Aggregate
import com.stack.knowledge.domain.mission.domain.constant.MissionStatus
import java.util.*

@Aggregate
data class Mission(
    val id: UUID,
    val title: String,
    val content: String,
    val timeLimit: Int,
    val point: Int,
    val missionStatus: MissionStatus,
    val userId: UUID
)