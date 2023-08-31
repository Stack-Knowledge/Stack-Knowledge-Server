package com.stack.knowledge.domain.point.application.spi

import com.stack.knowledge.domain.mission.domain.Mission
import com.stack.knowledge.domain.point.domain.Point

interface QueryPointPort {
    fun queryPointByMission(mission: Mission): Point?
    fun queryPointTopByIdDesc(): Point?
}