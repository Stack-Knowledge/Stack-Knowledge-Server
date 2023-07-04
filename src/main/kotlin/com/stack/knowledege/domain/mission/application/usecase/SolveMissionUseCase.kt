package com.stack.knowledege.domain.mission.application.usecase

import com.stack.knowledege.domain.mission.application.spi.MissionPort
import com.stack.knowledege.domain.mission.exception.MissionNotFoundException
import com.stack.knowledege.domain.mission.presentation.data.request.SolveMissionRequest
import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class SolveMissionUseCasee(
    private val missionPort: MissionPort,
    private val queryUserPort: QueryUserPort
) {
    fun execute(id: UUID, solveMissionRequest: SolveMissionRequest) {
        val mission = missionPort.queryMissionById(id)
            ?: throw MissionNotFoundException()
        val user = queryUserPort.queryCurrentUser()
    }
}