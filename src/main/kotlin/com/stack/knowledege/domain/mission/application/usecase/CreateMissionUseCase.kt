package com.stack.knowledege.domain.mission.application.usecase

import com.stack.knowledege.domain.mission.application.spi.CommandMissionPort
import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.mission.domain.constant.MissionStatus
import com.stack.knowledege.domain.mission.presentation.data.request.CreateMissionRequest
import com.stack.knowledege.domain.user.application.spi.UserPort
import com.stack.knowledege.domain.user.application.validator.UserValidator
import com.stack.knowledege.global.annotation.usecase.UseCase
import java.util.UUID

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
            missionStatus = MissionStatus.OPENED, // 회의로 결정
            userId = user.id
        )

        commandMissionPort.save(mission)
    }
}