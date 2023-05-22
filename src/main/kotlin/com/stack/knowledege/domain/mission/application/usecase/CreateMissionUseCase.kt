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
            title = createMissionRequest.title,
            content = createMissionRequest.content,
            duration = createMissionRequest.duration,
            timeLimit = createMissionRequest.timeLimit,
            isSolved = false,
            user = UserResponse(
                id = user.id,
                name = user.name,
                grade = user.grade,
                number = user.number
            )
        )

        commandMissionPort.saveMission(mission)
    }
}