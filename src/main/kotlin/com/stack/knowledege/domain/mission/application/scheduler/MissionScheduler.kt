package com.stack.knowledege.domain.mission.application.scheduler

import com.stack.knowledege.domain.mission.application.spi.MissionPort
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class MissionScheduler(
    private val missionPort: MissionPort
) {
//    @Scheduled
//    fun resetAllMission()
//
//    private fun
}