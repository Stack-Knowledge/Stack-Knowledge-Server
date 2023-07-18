package com.stack.knowledege.domain.mission.application.spi

import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.mission.domain.constant.MissionStatus
import com.stack.knowledege.domain.user.domain.User
import java.util.UUID

interface QueryMissionPort {
    fun queryAllMission(): List<Mission>
    fun queryMissionById(missionId: UUID): Mission?
    fun queryMissionByUser(user: User): Mission?
    fun queryMissionByMissionStatus(missionStatus: MissionStatus): List<Mission>
}