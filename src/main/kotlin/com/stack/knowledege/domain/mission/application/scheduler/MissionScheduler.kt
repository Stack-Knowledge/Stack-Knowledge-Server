package com.stack.knowledege.domain.mission.application.scheduler

import com.stack.knowledege.domain.mission.application.spi.MissionPort
import com.stack.knowledege.domain.mission.domain.constant.MissionStatus
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class MissionScheduler(
    private val missionPort: MissionPort
) {
    @Scheduled(cron = "30 12 * * *", zone = "Asia/Seoul")
    fun openAllMission() = checkAndChangeMissionStatusOpened()

    @Scheduled(cron = "30 7 * * *", zone = "Asia/Seoul")
    fun closeAllMission() = checkAndChangeMissionStatusClosed()

    private fun checkAndChangeMissionStatusOpened() {
        val missions = missionPort.queryAllMission()

        missions.map {
            it.copy(missionStatus = MissionStatus.CLOSED)
        }
    }

    private fun checkAndChangeMissionStatusClosed() {
        val missions = missionPort.queryAllMission()

        missions.map {
            it.copy(missionStatus = MissionStatus.OPENED)
        }
    }
}