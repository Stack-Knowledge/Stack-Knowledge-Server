package com.stack.knowledge.domain.mission.application.service

import com.stack.knowledge.common.annotation.service.ServiceWithTransaction
import com.stack.knowledge.common.service.SecurityService
import com.stack.knowledge.domain.mission.application.spi.CommandMissionPort
import com.stack.knowledge.domain.mission.domain.Mission
import com.stack.knowledge.domain.mission.domain.constant.MissionStatus
import com.stack.knowledge.domain.mission.presentation.data.request.CreateMissionRequest
import java.util.*

@ServiceWithTransaction
class CreateMissionService(
    private val commandMissionPort: CommandMissionPort,
    private val securityService: SecurityService
) {
    fun execute(createMissionRequest: CreateMissionRequest): Mission {
        val userId = securityService.queryCurrentUserId()

        val mission = Mission(
            id = UUID(0, 0),
            title = createMissionRequest.title,
            content = createMissionRequest.content,
            timeLimit = createMissionRequest.timeLimit,
            point = 1000,
            missionStatus = MissionStatus.OPENED,
//            missionStatus = queryMissionStatusBasedOnTime(),
            userId = userId
        )

        return commandMissionPort.save(mission)
    }

//    private fun queryMissionStatusBasedOnTime(): MissionStatus {
//        val time = LocalTime.now()
//        return if (time in LocalTime.of(12, 30)..LocalTime.of(19, 30)) {
//            MissionStatus.OPENED
//        } else {
//            MissionStatus.AVAILABLE_OPEN
//        }
//    }
}