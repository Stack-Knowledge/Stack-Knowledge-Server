package com.stack.knowledege.domain.mission.application.spi

import com.stack.knowledege.domain.mission.domain.Mission
import java.util.UUID

interface QueryMissionPort {
    fun queryAllMission(): List<Mission>
    fun queryMissionById(missionId: UUID): Mission?
}