package com.stack.knowledge.domain.user.application.usecase

import com.stack.knowledge.domain.mission.application.spi.QueryMissionPort
import com.stack.knowledge.domain.mission.exception.MissionNotFoundException
import com.stack.knowledge.domain.solve.application.spi.SolvePort
import com.stack.knowledge.domain.solve.domain.constant.SolveStatus
import com.stack.knowledge.domain.solve.exception.AlreadyScoredException
import com.stack.knowledge.domain.solve.exception.ForBiddenCommandSolveException
import com.stack.knowledge.domain.solve.exception.SolveNotFoundException
import com.stack.knowledge.domain.solve.exception.UnsupportedSolveStatusException
import com.stack.knowledge.domain.student.application.spi.CommandStudentPort
import com.stack.knowledge.domain.student.application.spi.QueryStudentPort
import com.stack.knowledge.domain.student.exception.StudentNotFoundException
import com.stack.knowledge.domain.user.domain.constant.Authority
import com.stack.knowledge.domain.user.presentation.data.request.ScoreSolveRequest
import com.stack.knowledge.common.annotation.usecase.UseCase
import com.stack.knowledge.common.service.SecurityService
import com.stack.knowledge.domain.mission.application.spi.CommandMissionPort
import com.stack.knowledge.domain.point.application.spi.QueryPointPort
import com.stack.knowledge.domain.point.exception.PointNotFoundException
import java.util.UUID

@UseCase
class ScoreSolveUseCase(
    private val solvePort: SolvePort,
    private val queryStudentPort: QueryStudentPort,
    private val commandStudentPort: CommandStudentPort,
    private val queryMissionPort: QueryMissionPort,
    private val securityService: SecurityService,
    private val commandMissionPort: CommandMissionPort,
    private val queryPointPort: QueryPointPort
) {
    fun execute(solveId: UUID, scoreSolveRequest: ScoreSolveRequest) {
        val solve = solvePort.querySolveById(solveId) ?: throw SolveNotFoundException()
        val user = securityService.queryCurrentUser()
        val student = queryStudentPort.queryStudentById(solve.student) ?: throw StudentNotFoundException()
        val mission = queryMissionPort.queryMissionById(solve.mission) ?: throw MissionNotFoundException()
        val point = queryPointPort.queryPointByMission(mission) ?: throw PointNotFoundException()

        if (solve.solveStatus != SolveStatus.SCORING)
            throw AlreadyScoredException()

        if (user.authority != Authority.ROLE_TEACHER)
            throw ForBiddenCommandSolveException()

        val currentPoint = when (scoreSolveRequest.solveStatus) {
            SolveStatus.CORRECT_ANSWER -> {
                commandMissionPort.save(mission.copy(point = point.missionPoint))
                student.currentPoint.plus(point.missionPoint)
            }
            SolveStatus.WRONG_ANSWER -> student.currentPoint
            else -> throw UnsupportedSolveStatusException()
        }

        val cumulatePoint = when (scoreSolveRequest.solveStatus) {
            SolveStatus.CORRECT_ANSWER -> {
                commandMissionPort.save(mission.copy(point = point.missionPoint))
                student.cumulatePoint.plus(point.missionPoint)
            }
            SolveStatus.WRONG_ANSWER -> student.cumulatePoint
            else -> throw UnsupportedSolveStatusException()
        }

        commandStudentPort.save(student.copy(currentPoint = currentPoint, cumulatePoint = cumulatePoint))
        solvePort.save(solve.copy(solveStatus = scoreSolveRequest.solveStatus))
    }
}