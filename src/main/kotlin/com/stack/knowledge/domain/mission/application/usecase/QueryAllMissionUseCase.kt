package com.stack.knowledge.domain.mission.application.usecase

import com.stack.knowledge.domain.mission.application.spi.QueryMissionPort
import com.stack.knowledge.domain.mission.presentation.data.response.MissionResponse
import com.stack.knowledge.domain.user.application.spi.QueryUserPort
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import com.stack.knowledge.domain.user.presentation.data.response.UserResponse
import com.stack.knowledge.common.annotation.usecase.ReadOnlyUseCase
import com.stack.knowledge.domain.mission.domain.constant.MissionStatus
import com.stack.knowledge.domain.solve.application.spi.QuerySolvePort

@ReadOnlyUseCase
class QueryAllMissionUseCase(
    private val queryMissionPort: QueryMissionPort,
    private val queryUserPort: QueryUserPort,
    private val querySolvePort: QuerySolvePort
) {
    fun execute(): List<MissionResponse> {
        val missions = queryMissionPort.queryAllMissionByMissionStatus(MissionStatus.OPENED)

        return missions.mapNotNull { mission ->
            val solve = querySolvePort.queryAllSolveByMission(mission).find { it.mission == mission.id }

            solve?.let {
                val user = queryUserPort.queryUserById(mission.userId) ?: throw UserNotFoundException()

                MissionResponse(
                    id = mission.id,
                    title = mission.title,
                    point = mission.point,
                    missionStatus = mission.missionStatus,
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
}