package com.stack.knowledge.domain.mission.application.usecase

import com.stack.knowledge.domain.mission.application.spi.CommandMissionPort
import com.stack.knowledge.domain.mission.domain.Mission
import com.stack.knowledge.domain.mission.domain.constant.MissionStatus
import com.stack.knowledge.domain.mission.presentation.data.request.CreateMissionRequest
import com.stack.knowledge.common.annotation.usecase.UseCase
import com.stack.knowledge.common.service.SecurityService
import java.time.LocalTime
import java.util.*

@UseCase
class CreateMissionUseCase(
    private val commandMissionPort: CommandMissionPort,
    private val securityService: SecurityService
) {
    fun execute(createMissionRequest: CreateMissionRequest) {
        val user = securityService.queryCurrentUser()

        val mission = Mission(
            id = UUID.randomUUID(),
            title = createMissionRequest.title,
            content = createMissionRequest.content,
            timeLimit = createMissionRequest.timeLimit,
            point = 1000,
            missionStatus = MissionStatus.OPENED,
//            missionStatus = queryMissionStatusBasedOnTime(),
            userId = user.id
        )

        commandMissionPort.save(mission)
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