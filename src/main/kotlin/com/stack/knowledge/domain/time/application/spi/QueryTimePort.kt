package com.stack.knowledge.domain.time.application.spi

import com.stack.knowledge.domain.mission.domain.Mission
import com.stack.knowledge.domain.time.domain.Time

interface QueryTimePort {
    fun queryTimeByMission(mission: Mission): Time?
}