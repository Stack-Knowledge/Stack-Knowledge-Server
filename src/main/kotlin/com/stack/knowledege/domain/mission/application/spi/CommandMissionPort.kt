package com.stack.knowledege.domain.mission.application.spi

import com.stack.knowledege.domain.mission.domain.Mission

interface CommandMissionPort {
    fun saveMission(mission: Mission)
}