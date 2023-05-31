package com.stack.knowledege.domain.mission.application.usecase

import com.stack.knowledege.domain.mission.application.spi.CommandMissionPort
import com.stack.knowledege.domain.mission.domain.Mission
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
            introduce = createMissionRequest.introduce,
            title = createMissionRequest.title,
            content = createMissionRequest.content,
            duration = createMissionRequest.duration,
            timeLimit = createMissionRequest.timeLimit,
            isSolved = false,
            userId = user.id
        )

        commandMissionPort.save(mission)
    }
}