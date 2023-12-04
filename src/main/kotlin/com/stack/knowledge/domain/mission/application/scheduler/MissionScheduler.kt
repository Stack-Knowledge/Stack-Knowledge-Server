package com.stack.knowledge.domain.mission.application.scheduler

import com.stack.knowledge.domain.mission.application.spi.MissionPort
import com.stack.knowledge.domain.mission.domain.constant.MissionStatus
import com.stack.knowledge.domain.point.application.spi.CommandPointPort
import com.stack.knowledge.domain.time.application.spi.CommandTimePort
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class MissionScheduler(
    private val missionPort: MissionPort,
    private val commandTimePort: CommandTimePort,
    private val commandPointPort: CommandPointPort
) {
    @Scheduled(cron = "0 30 12 ? * 1-5")
    fun openAllMission() = checkAndChangeMissionStatusOpened()

    @Scheduled(cron = "0 30 19 ? * 1-5")
    fun closeAllMission() = checkAndChangeMissionStatusClosed()

    private fun checkAndChangeMissionStatusOpened() {
        val missions = missionPort.queryAllMissionByMissionStatus(MissionStatus.AVAILABLE_OPEN)

        missions.map {
            missionPort.save(it.copy(missionStatus = MissionStatus.OPENED))
        }
    }

    private fun checkAndChangeMissionStatusClosed() {
        val missions = missionPort.queryAllMissionByMissionStatus(MissionStatus.OPENED)
        val missionIds = missions.map { it.id }

        commandTimePort.deleteAllByMissionIds(missionIds)
        commandPointPort.deleteAllByMissionIds(missionIds)

        missions.map {
            missionPort.save(it.copy(missionStatus = MissionStatus.CLOSED))
        }
    }
}