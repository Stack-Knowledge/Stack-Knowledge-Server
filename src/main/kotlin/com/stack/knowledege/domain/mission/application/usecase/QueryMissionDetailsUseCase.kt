package com.stack.knowledege.domain.mission.application.usecase

import com.stack.knowledege.domain.mission.application.spi.MissionPort
import com.stack.knowledege.domain.mission.exception.AlreadySolvedMissionException
import com.stack.knowledege.domain.mission.exception.MissionNotFoundException
import com.stack.knowledege.domain.mission.presentation.data.response.MissionDetailsResponse
import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.domain.user.presentation.data.response.UserResponse
import com.stack.knowledege.global.annotation.usecase.ReadOnlyUseCase
import java.util.UUID

@ReadOnlyUseCase
class QueryMissionDetailsUseCase(
    private val missionPort: MissionPort,
    private val queryUserPort: QueryUserPort
) {
    fun execute(id: UUID): MissionDetailsResponse {
        val mission = missionPort.queryMissionById(id)
            ?: throw MissionNotFoundException()
        val user = queryUserPort.queryUserById(mission.userId)
            ?: throw UserNotFoundException()

        if (mission.isSolved) {
            throw AlreadySolvedMissionException()
        }

        return MissionDetailsResponse(
            title = mission.title,
            content = mission.content,
            timeLimit = mission.timeLimit,
            user = UserResponse(
                id = user.id,
                email = user.email,
                name = user.email,
                profileImage = user.profileImage
            )
        )
    }
}