package com.stack.knowledge.domain.solve.application.usecase

import com.stack.knowledge.common.service.SecurityService
import com.stack.knowledge.domain.mission.application.spi.MissionPort
import com.stack.knowledge.domain.mission.domain.Mission
import com.stack.knowledge.domain.mission.domain.constant.MissionStatus
import com.stack.knowledge.domain.mission.exception.MissionNotFoundException
import com.stack.knowledge.domain.mission.exception.MissionNotOpenedException
import com.stack.knowledge.domain.point.application.spi.PointPort
import com.stack.knowledge.domain.point.domain.Point
import com.stack.knowledge.domain.solve.application.spi.SolvePort
import com.stack.knowledge.domain.solve.domain.Solve
import com.stack.knowledge.domain.solve.domain.constant.SolveStatus
import com.stack.knowledge.domain.solve.exception.AlreadySolvedException
import com.stack.knowledge.domain.solve.exception.SolveNotFoundException
import com.stack.knowledge.domain.solve.presentation.data.request.SolveMissionRequest
import com.stack.knowledge.domain.time.application.spi.QueryTimePort
import com.stack.knowledge.domain.time.exception.TimeLimitExceededException
import com.stack.knowledge.domain.time.exception.TimeNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration
import java.time.LocalDateTime
import java.util.*

@Service
class SolveMissionUseCase(
    private val missionPort: MissionPort,
    private val solvePort: SolvePort,
    private val securityService: SecurityService,
    private val pointPort: PointPort,
    private val queryTimePort: QueryTimePort
) {
    @Transactional(noRollbackFor = [TimeLimitExceededException::class])
    fun execute(id: UUID, solveMissionRequest: SolveMissionRequest) {
        val mission = missionPort.queryMissionById(id) ?: throw MissionNotFoundException()
        val studentId = securityService.queryCurrentUserId()

        if (mission.missionStatus != MissionStatus.OPENED)
            throw MissionNotOpenedException()

        if (solvePort.existByStudentIdAndMissionId(studentId, mission.id))
            throw AlreadySolvedException()

        val time = queryTimePort.queryTimeByMission(mission) ?: throw TimeNotFoundException()
        val timeElapsed = (Duration.between(time.createdAt, LocalDateTime.now())).toSeconds()

        val (solve, isExceeded) = createSolve(timeElapsed, mission, solveMissionRequest.solvation, studentId)

        if (isExceeded)
            throw TimeLimitExceededException()

        val topPoint = (pointPort.queryTopByMissionIdOrderByMissionPointDesc(mission.id)?.missionPoint?.times(0.97))?.toInt()

        val point = Point(
            missionPoint = topPoint ?: 1000,
            mission = mission.id,
            solve = solve.id
        )
        pointPort.save(point)
    }

    private fun createSolve(timeElapsed: Long, mission: Mission, solvation: String, studentId: UUID): Pair<Solve, Boolean> {
        val (solveStatus, isExceeded) = when {
            timeElapsed > mission.timeLimit + 1 -> Pair(SolveStatus.WRONG_ANSWER, true)
            else -> Pair(SolveStatus.SCORING, false)
        }

        val solve = Solve(
            id = UUID.randomUUID(),
            solvation = solvation,
            solveStatus = solveStatus,
            student = studentId,
            mission = mission.id
        )
        val saveSolve = solvePort.save(solve) ?: throw SolveNotFoundException()

        return Pair(saveSolve, isExceeded)
    }
}