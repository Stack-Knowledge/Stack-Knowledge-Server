package com.stack.knowledge.domain.mission.application.service

import com.stack.knowledge.common.annotation.usecase.ReadOnlyUseCase
import com.stack.knowledge.common.spi.SecurityPort
import com.stack.knowledge.domain.mission.application.spi.QueryMissionPort
import com.stack.knowledge.domain.mission.domain.constant.MissionStatus
import com.stack.knowledge.domain.mission.presentation.data.response.MissionResponse
import com.stack.knowledge.domain.solve.application.spi.QuerySolvePort
import com.stack.knowledge.domain.user.application.spi.QueryUserPort
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import com.stack.knowledge.domain.user.presentation.data.response.UserResponse

@ReadOnlyUseCase
class QueryAllMissionService(
    private val queryMissionPort: QueryMissionPort,
    private val queryUserPort: QueryUserPort,
    private val querySolvePort: QuerySolvePort,
    private val securityPort: SecurityPort
) {
    fun execute(): List<MissionResponse> {
        val studentId = securityPort.queryCurrentUserId()
        val solvedMissionIds = querySolvePort.queryAllSolveByStudentId(studentId).map {
            it.mission
        }

        val missions = queryMissionPort.queryAllMissionByMissionStatusOrderByCreatedAtDesc(MissionStatus.OPENED).filterNot {
            it.id in solvedMissionIds
        }

        return missions.map {
            val user = queryUserPort.queryUserById(it.userId) ?: throw UserNotFoundException()

            MissionResponse(
                id = it.id,
                title = it.title,
                point = it.point,
                missionStatus = it.missionStatus,
                user = UserResponse(
                    id = user.id,
                    name = user.name,
                    profileImage = user.profileImage
                )
            )
        }
    }
}