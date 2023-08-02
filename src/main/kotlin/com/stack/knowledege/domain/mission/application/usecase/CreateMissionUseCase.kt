package com.stack.knowledege.domain.mission.application.usecase

import com.stack.knowledege.domain.mission.application.spi.CommandMissionPort
import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.mission.domain.constant.MissionStatus
import com.stack.knowledege.domain.mission.presentation.data.request.CreateMissionRequest
import com.stack.knowledege.domain.user.application.spi.UserPort
import com.stack.knowledege.domain.user.application.validator.UserValidator
import com.stack.knowledege.domain.common.annotation.usecase.UseCase
import java.time.LocalTime
import java.util.*

@UseCase
class CreateMissionUseCase(
    private val userPort: UserPort,
    private val commandMissionPort: CommandMissionPort,
    private val userValidator: UserValidator
) {
    fun execute(createMissionRequest: CreateMissionRequest) {
        val user = userPort.queryCurrentUser()

        userValidator.validateUserTeacher(user)

        val mission = Mission(
            id = UUID.randomUUID(),
            title = createMissionRequest.title,
            content = createMissionRequest.content,
            timeLimit = createMissionRequest.timeLimit,
            point = 1000,
            missionStatus = queryMissionStatusBasedOnTime(),
            userId = user.id
        )

        commandMissionPort.save(mission)
    }

    private fun queryMissionStatusBasedOnTime(): MissionStatus {
        val time = LocalTime.now()
        return if (time in LocalTime.of(12, 30)..LocalTime.of(19, 30)) {
            MissionStatus.OPENED
        } else {
            MissionStatus.AVAILABLE_OPEN
        }
    }
}