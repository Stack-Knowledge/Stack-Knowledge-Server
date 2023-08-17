package com.stack.knowledege.domain.solve.application.usecase

import com.stack.knowledege.domain.mission.application.spi.MissionPort
import com.stack.knowledege.domain.mission.domain.constant.MissionStatus
import com.stack.knowledege.domain.mission.exception.MissionNotFoundException
import com.stack.knowledege.domain.mission.exception.MissionNotOpenedException
import com.stack.knowledege.domain.solve.application.spi.CommandSolvePort
import com.stack.knowledege.domain.solve.presentation.data.request.SolveMissionRequest
import com.stack.knowledege.domain.solve.domain.Solve
import com.stack.knowledege.domain.solve.domain.constant.SolveStatus
import com.stack.knowledege.domain.solve.exception.StudentOnlyException
import com.stack.knowledege.domain.student.application.spi.QueryStudentPort
import com.stack.knowledege.domain.student.exception.StudentNotFoundException
import com.stack.knowledege.domain.user.domain.constant.Authority
import com.stack.knowledege.common.annotation.usecase.UseCase
import com.stack.knowledege.common.service.SecurityService
import java.util.UUID

@UseCase
class SolveMissionUseCase(
    private val queryStudentPort: QueryStudentPort,
    private val missionPort: MissionPort,
    private val commandSolvePort: CommandSolvePort,
    private val securityService: SecurityService
) {
    fun execute(id: UUID, solveMissionRequest: SolveMissionRequest) {
        val mission = missionPort.queryMissionById(id) ?: throw MissionNotFoundException()
        val user = securityService.queryCurrentUser()

        if (mission.missionStatus == MissionStatus.CLOSED)
            throw MissionNotOpenedException()

        if (user.authority != Authority.ROLE_STUDENT)
            throw StudentOnlyException()

        val student = queryStudentPort.queryStudentByUser(user) ?: throw StudentNotFoundException()

        val solve = Solve(
            id = UUID.randomUUID(),
            solvation = solveMissionRequest.solvation,
            solveStatus = SolveStatus.SCORING,
            student = student.id,
            mission = mission.id
        )

        commandSolvePort.save(solve)
    }
}