package com.stack.knowledege.domain.mission.application.usecase

import com.stack.knowledege.domain.mission.application.spi.CommandMissionPort
import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.mission.presentation.data.request.CreateMissionRequest
import com.stack.knowledege.domain.user.application.spi.UserPort
import com.stack.knowledege.domain.user.presentation.data.response.UserResponse
import com.stack.knowledege.global.annotation.usecase.UseCase
import java.util.*

@UseCase
class CreateMissionUseCase(
    private val userPort: UserPort,
    private val commandMissionPort: CommandMissionPort
) {
    fun execute(createMissionRequest: CreateMissionRequest) {
        val user = userPort.queryCurrentUser()

        val mission = Mission(
            id = UUID.randomUUID(),
            introduce = createMissionRequest.introduce,
            title = createMissionRequest.title,
            content = createMissionRequest.content,
            timeLimit = createMissionRequest.timeLimit,
            isSolved = false,
            userId = user.id
        )

        commandMissionPort.saveMission(mission)
    }
}