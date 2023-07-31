package com.stack.knowledege.domain.user.application.usecase

import com.stack.knowledege.domain.mission.application.spi.QueryMissionPort
import com.stack.knowledege.domain.mission.exception.MissionNotFoundException
import com.stack.knowledege.domain.solve.application.spi.SolvePort
import com.stack.knowledege.domain.solve.domain.constant.SolveStatus
import com.stack.knowledege.domain.solve.exception.AlreadyScoredException
import com.stack.knowledege.domain.solve.exception.ForBiddenCommandSolveException
import com.stack.knowledege.domain.solve.exception.SolveNotFoundException
import com.stack.knowledege.domain.solve.exception.UnsupportedSolveStatusException
import com.stack.knowledege.domain.student.application.spi.CommandStudentPort
import com.stack.knowledege.domain.student.application.spi.QueryStudentPort
import com.stack.knowledege.domain.student.exception.StudentNotFoundException
import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.domain.user.domain.constant.Authority
import com.stack.knowledege.domain.user.presentation.data.request.ScoreSolveRequest
import com.stack.knowledege.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class ScoreSolveUseCase(
    private val solvePort: SolvePort,
    private val queryUserPort: QueryUserPort,
    private val queryStudentPort: QueryStudentPort,
    private val commandStudentPort: CommandStudentPort,
    private val queryMissionPort: QueryMissionPort
) {
    fun execute(solveId: UUID, scoreSolveRequest: ScoreSolveRequest) {
        val solve = solvePort.querySolveById(solveId) ?: throw SolveNotFoundException()
        val user = queryUserPort.queryCurrentUser()
        val student = queryStudentPort.queryStudentById(solve.student) ?: throw StudentNotFoundException()
        val mission = queryMissionPort.queryMissionById(solve.mission) ?: throw MissionNotFoundException()

        if (solve.solveStatus != SolveStatus.SCORING)
            throw AlreadyScoredException()

        if (user.authority != Authority.ROLE_TEACHER)
            throw ForBiddenCommandSolveException()

        val currentPoint = when (scoreSolveRequest.solveStatus) {
            SolveStatus.CORRECT_ANSWER -> student.currentPoint.plus(mission.point)
            SolveStatus.WRONG_ANSWER -> student.currentPoint
            else -> { throw UnsupportedSolveStatusException() }
        }

        val cumulatePoint = when (scoreSolveRequest.solveStatus) {
            SolveStatus.CORRECT_ANSWER -> student.cumulatePoint.plus(mission.point)
            SolveStatus.WRONG_ANSWER -> student.cumulatePoint
            else -> { throw UnsupportedSolveStatusException() }
        }

        commandStudentPort.save(student.copy(currentPoint = currentPoint, cumulatePoint = cumulatePoint))
        solvePort.save(solve.copy(solveStatus = scoreSolveRequest.solveStatus))
    }
}