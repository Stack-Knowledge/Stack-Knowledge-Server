package com.stack.knowledge.domain.mission.application.spi

import com.stack.knowledge.domain.mission.domain.Mission

interface CommandMissionPort {
    fun save(mission: Mission)
}