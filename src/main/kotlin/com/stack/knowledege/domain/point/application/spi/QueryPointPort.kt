package com.stack.knowledege.domain.point.application.spi

import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.point.domain.Point

interface QueryPointPort {
    fun queryPointByMission(mission: Mission): Point?
}