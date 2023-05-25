package com.stack.knowledege.domain.mission.application.usecase

import com.stack.knowledege.domain.mission.application.spi.QueryMissionPort
import com.stack.knowledege.domain.mission.presentation.data.response.MissionResponse
import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.domain.user.presentation.data.response.UserResponse
import com.stack.knowledege.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryAllMissionUseCase(
    private val queryMissionPort: QueryMissionPort,
    private val queryUserPort: QueryUserPort
) {
    fun execute(): List<MissionResponse> {
        val notices = queryMissionPort.queryAllMission()

        return notices.map{
            val user = queryUserPort.queryUserById(it.id)
            MissionResponse(
                id = it.id,
                title = it.title,
                introduce = it.introduce,
                isSolved = it.isSolved,
                user = UserResponse(
                    id = user.id,
                    name = user.name,
                    grade = user.grade,
                    classes = user.classes,
                    number = user.number
                )
            )
        }
    }
}