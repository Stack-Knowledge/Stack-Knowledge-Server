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
import com.stack.knowledge.domain.student.application.spi.QueryStudentPort
import com.stack.knowledge.domain.student.exception.StudentNotFoundException
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
    private val queryTimePort: QueryTimePort,
    private val queryStudentPort: QueryStudentPort
) {
    @Transactional(noRollbackFor = [TimeLimitExceededException::class], rollbackFor = [Exception::class])
    fun execute(id: UUID, solveMissionRequest: SolveMissionRequest) {
        val student = securityService.queryCurrentUserId().let {
            queryStudentPort.queryStudentById(it) ?: throw StudentNotFoundException()
        }
        val mission = missionPort.queryMissionById(id) ?: throw MissionNotFoundException()

        if (mission.missionStatus != MissionStatus.OPENED)
            throw MissionNotOpenedException()

        if (solvePort.existByStudentIdAndMissionId(student.id, mission.id))
            throw AlreadySolvedException()

        val time = queryTimePort.queryTimeByMissionAndStudent(mission, student) ?: throw TimeNotFoundException()
        val timeElapsed = (Duration.between(time.createdAt, LocalDateTime.now())).toSeconds()

        val solve = createSolve(timeElapsed, mission, solveMissionRequest.solvation, student.id)

        val topPoint = (pointPort.queryTopByMissionIdOrderByMissionPoint(mission.id)?.missionPoint?.times(0.97))?.toInt()

        val point = Point(
            missionPoint = topPoint ?: 1000,
            mission = mission.id,
            solve = solve.id
        )
        pointPort.save(point)
    }

    private fun createSolve(timeElapsed: Long, mission: Mission, solvation: String, studentId: UUID): Solve {
        val solveStatus = when {
            timeElapsed > mission.timeLimit + 5 -> {
                SolveStatus.WRONG_ANSWER
                throw TimeLimitExceededException()
            }
            else -> SolveStatus.SCORING
        }

        val solve = Solve(
            id = UUID.randomUUID(),
            solvation = solvation,
            solveStatus = solveStatus,
            student = studentId,
            mission = mission.id
        )

        return solvePort.save(solve) ?: throw SolveNotFoundException()
    }
}