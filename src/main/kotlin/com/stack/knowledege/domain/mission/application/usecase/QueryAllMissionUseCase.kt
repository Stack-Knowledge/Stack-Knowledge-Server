package com.stack.knowledege.domain.mission.application.usecase

import com.stack.knowledege.domain.mission.application.spi.QueryMissionPort
import com.stack.knowledege.domain.mission.presentation.data.response.MissionResponse
import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.domain.user.presentation.data.response.UserResponse
import com.stack.knowledege.domain.common.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryAllMissionUseCase(
    private val queryMissionPort: QueryMissionPort,
    private val queryUserPort: QueryUserPort
) {
    fun execute(): List<MissionResponse> {
        val missions = queryMissionPort.queryAllMission()

        return missions.map{
            val user = queryUserPort.queryUserById(it.userId) ?: throw UserNotFoundException()

            MissionResponse(
                id = it.id,
                title = it.title,
                point = it.point,
                user = UserResponse(
                    id = user.id,
                    email = user.email,
                    name = user.name,
                    profileImage = user.profileImage
                )
            )
        }
    }
}