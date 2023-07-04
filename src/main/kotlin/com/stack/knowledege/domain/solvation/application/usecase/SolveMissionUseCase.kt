package com.stack.knowledege.domain.solvation.application.usecase

import com.stack.knowledege.domain.mission.application.spi.MissionPort
import com.stack.knowledege.domain.mission.exception.MissionNotFoundException
import com.stack.knowledege.domain.mission.presentation.data.request.SolveMissionRequest
import com.stack.knowledege.domain.solvation.application.spi.SolvationPort
import com.stack.knowledege.domain.solvation.domain.Solvation
import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class SolveMissionUseCase(
    private val queryUserPort: QueryUserPort,
    private val missionPort: MissionPort,
    private val solvationPort: SolvationPort
) {
    fun execute(id: UUID, solveMissionRequest: SolveMissionRequest) {
        val mission = missionPort.queryMissionById(id)
            ?: throw MissionNotFoundException()
        val user = queryUserPort.queryCurrentUser()

        val solvation = Solvation(
            id = UUID.randomUUID(),
            answer = solveMissionRequest.answer,
            user = user.id,
            mission = mission.id
        )

        solvationPort.save(solvation)
    }
}