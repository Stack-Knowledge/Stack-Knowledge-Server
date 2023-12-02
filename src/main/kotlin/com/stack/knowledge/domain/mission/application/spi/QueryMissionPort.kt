package com.stack.knowledge.domain.mission.application.spi

import com.stack.knowledge.domain.mission.domain.Mission
import com.stack.knowledge.domain.mission.domain.constant.MissionStatus
import java.util.*

interface QueryMissionPort {
    fun queryMissionById(missionId: UUID): Mission?
    fun queryAllMissionByMissionStatus(missionStatus: MissionStatus): List<Mission>
    fun queryAllMissionsByUserIdOrderByCreatedAtDesc(userId: UUID): List<Mission>
    fun queryAllMissionByMissionStatusOrderByCreatedAtDesc(missionStatus: MissionStatus): List<Mission>
}