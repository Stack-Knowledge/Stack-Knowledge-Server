package com.stack.knowledge.domain.user.application.service

import com.stack.knowledge.common.annotation.service.ServiceWithTransaction
import com.stack.knowledge.domain.mission.application.spi.MissionPort
import com.stack.knowledge.domain.mission.domain.Mission
import com.stack.knowledge.domain.mission.exception.MissionNotFoundException
import com.stack.knowledge.domain.point.application.spi.QueryPointPort
import com.stack.knowledge.domain.point.domain.Point
import com.stack.knowledge.domain.point.exception.PointNotFoundException
import com.stack.knowledge.domain.solve.application.spi.SolvePort
import com.stack.knowledge.domain.solve.domain.constant.SolveStatus
import com.stack.knowledge.domain.solve.exception.AlreadyScoredException
import com.stack.knowledge.domain.solve.exception.SolveNotFoundException
import com.stack.knowledge.domain.solve.exception.UnsupportedSolveStatusException
import com.stack.knowledge.domain.student.application.spi.CommandStudentPort
import com.stack.knowledge.domain.student.application.spi.QueryStudentPort
import com.stack.knowledge.domain.student.domain.Student
import com.stack.knowledge.domain.student.exception.StudentNotFoundException
import com.stack.knowledge.domain.user.presentation.data.request.ScoreSolveRequest
import java.util.*

@ServiceWithTransaction
class ScoreSolveService(
    private val solvePort: SolvePort,
    private val queryStudentPort: QueryStudentPort,
    private val commandStudentPort: CommandStudentPort,
    private val missionPort: MissionPort,
    private val queryPointPort: QueryPointPort
) {
    fun execute(solveId: UUID, scoreSolveRequest: ScoreSolveRequest) {
        val solve = solvePort.querySolveById(solveId) ?: throw SolveNotFoundException()
        val student = queryStudentPort.queryStudentById(solve.student) ?: throw StudentNotFoundException()
        val mission = missionPort.queryMissionById(solve.mission) ?: throw MissionNotFoundException()
        val point = queryPointPort.queryPointBySolve(solve) ?: throw PointNotFoundException()

        if (solve.solveStatus != SolveStatus.SCORING)
            throw AlreadyScoredException()

        val (currentPoint, cumulatePoint) = calculatePoints(scoreSolveRequest.solveStatus, student, point, mission)

        commandStudentPort.save(student.copy(currentPoint = currentPoint, cumulatePoint = cumulatePoint))
        solvePort.save(solve.copy(solveStatus = scoreSolveRequest.solveStatus))
    }

    private fun calculatePoints(solveStatus: SolveStatus, student: Student, point: Point, mission: Mission): Pair<Int, Int> =
        when (solveStatus) {
            SolveStatus.CORRECT_ANSWER -> {
                val lowPoint = queryPointPort.queryTopByMissionIdOrderByMissionPoint(mission.id) ?: throw PointNotFoundException()
                missionPort.save(mission.copy(point = lowPoint.missionPoint))
                Pair(student.currentPoint + point.missionPoint, student.cumulatePoint + point.missionPoint)
            }
            SolveStatus.WRONG_ANSWER -> Pair(student.currentPoint, student.cumulatePoint)
            else -> throw UnsupportedSolveStatusException()
        }
}