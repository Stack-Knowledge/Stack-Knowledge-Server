package com.stack.knowledege.domain.solve.application.usecase

import com.stack.knowledege.domain.mission.application.spi.MissionPort
import com.stack.knowledege.domain.mission.exception.MissionNotFoundException
import com.stack.knowledege.domain.mission.presentation.data.request.SolveMissionRequest
import com.stack.knowledege.domain.solve.application.spi.SolvePort
import com.stack.knowledege.domain.solve.domain.Solve
import com.stack.knowledege.domain.solve.domain.constant.SolveStatus
import com.stack.knowledege.domain.student.application.spi.QueryStudentPort
import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class SolveMissionUseCase(
    private val queryUserPort: QueryUserPort,
    private val queryStudentPort: QueryStudentPort,
    private val missionPort: MissionPort,
    private val solvationPort: SolvePort
) {
    fun execute(id: UUID, solveMissionRequest: SolveMissionRequest) {
        val mission = missionPort.queryMissionById(id)
            ?: throw MissionNotFoundException()
        val student = queryUserPort.queryCurrentUser()
            .let { queryStudentPort.queryStudentByUser(it) }

        val solve = Solve(
            id = UUID.randomUUID(),
            solvation = solveMissionRequest.answer,
            isSolved = true,
            solveStatus = SolveStatus.SCORING,
            student = student.id,
            mission = mission.id
        )

        solvationPort.save(solve)
    }
}