package com.stack.knowledege.domain.mission.application.scheduler

import com.stack.knowledege.domain.mission.application.spi.MissionPort
import com.stack.knowledege.domain.mission.domain.constant.MissionStatus
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class MissionScheduler(
    private val missionPort: MissionPort
) {
    @Scheduled(cron = "0 30 12 ? * 1-5", zone = "Asia/Seoul")
    fun openAllMission() = checkAndChangeMissionStatusOpened()

    @Scheduled(cron = "0 30 19 ? * 1-5", zone = "Asia/Seoul")
    fun closeAllMission() = checkAndChangeMissionStatusClosed()

    private fun checkAndChangeMissionStatusOpened() {
        val missions = missionPort.queryMissionByMissionStatus(MissionStatus.AVAILABLE_OPEN)

        missions.map {
            missionPort.save(it.copy(missionStatus = MissionStatus.OPENED))
        }
    }

    private fun checkAndChangeMissionStatusClosed() {
        val missions = missionPort.queryMissionByMissionStatus(MissionStatus.OPENED)

        missions.map {
            missionPort.save(it.copy(missionStatus = MissionStatus.CLOSED))
        }
    }
}