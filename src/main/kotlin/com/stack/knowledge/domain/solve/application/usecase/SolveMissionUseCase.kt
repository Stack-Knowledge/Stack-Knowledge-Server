package com.stack.knowledge.domain.solve.application.usecase

import com.stack.knowledge.domain.mission.application.spi.MissionPort
import com.stack.knowledge.domain.mission.domain.constant.MissionStatus
import com.stack.knowledge.domain.mission.exception.MissionNotFoundException
import com.stack.knowledge.domain.mission.exception.MissionNotOpenedException
import com.stack.knowledge.domain.solve.application.spi.CommandSolvePort
import com.stack.knowledge.domain.solve.presentation.data.request.SolveMissionRequest
import com.stack.knowledge.domain.solve.domain.Solve
import com.stack.knowledge.domain.solve.domain.constant.SolveStatus
import com.stack.knowledge.domain.solve.exception.StudentOnlyException
import com.stack.knowledge.domain.student.application.spi.QueryStudentPort
import com.stack.knowledge.domain.student.exception.StudentNotFoundException
import com.stack.knowledge.domain.user.domain.constant.Authority
import com.stack.knowledge.common.annotation.usecase.UseCase
import com.stack.knowledge.common.service.SecurityService
import com.stack.knowledge.domain.point.application.spi.PointPort
import com.stack.knowledge.domain.point.domain.Point
import java.util.UUID

@UseCase
class SolveMissionUseCase(
    private val queryStudentPort: QueryStudentPort,
    private val missionPort: MissionPort,
    private val commandSolvePort: CommandSolvePort,
    private val securityService: SecurityService,
    private val pointPort: PointPort
) {
    fun execute(id: UUID, solveMissionRequest: SolveMissionRequest) {
        val mission = missionPort.queryMissionById(id) ?: throw MissionNotFoundException()
        val user = securityService.queryCurrentUser()

        if (mission.missionStatus != MissionStatus.OPENED)
            throw MissionNotOpenedException()

        if (user.authority != Authority.ROLE_STUDENT)
            throw StudentOnlyException()

        val student = queryStudentPort.queryStudentByUser(user) ?: throw StudentNotFoundException()

        val point = Point(
            missionPoint = ((pointPort.queryPointTopByIdDesc()?.missionPoint ?: 1000) * 0.97).toInt(),
            mission = mission.id
        )

        pointPort.save(point)

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