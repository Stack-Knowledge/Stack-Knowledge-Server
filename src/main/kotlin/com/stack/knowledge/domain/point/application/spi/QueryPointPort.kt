package com.stack.knowledge.domain.point.application.spi

import com.stack.knowledge.domain.mission.domain.Mission
import com.stack.knowledge.domain.point.domain.Point
import com.stack.knowledge.domain.solve.domain.Solve
import java.util.*

interface QueryPointPort {
    fun queryPointByMission(mission: Mission): Point?
    fun queryPointBySolve(solve: Solve): Point?
    fun queryTopByMissionIdOrderByMissionPointDesc(missionId: UUID): Point?
}