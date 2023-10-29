package com.stack.knowledge.domain.solve.application.usecase

import com.stack.knowledge.domain.mission.application.spi.MissionPort
import com.stack.knowledge.domain.mission.domain.constant.MissionStatus
import com.stack.knowledge.domain.mission.exception.MissionNotFoundException
import com.stack.knowledge.domain.mission.exception.MissionNotOpenedException
import com.stack.knowledge.domain.solve.presentation.data.request.SolveMissionRequest
import com.stack.knowledge.domain.solve.domain.Solve
import com.stack.knowledge.domain.solve.domain.constant.SolveStatus
import com.stack.knowledge.domain.student.application.spi.QueryStudentPort
import com.stack.knowledge.domain.student.exception.StudentNotFoundException
import com.stack.knowledge.common.annotation.usecase.UseCase
import com.stack.knowledge.common.service.SecurityService
import com.stack.knowledge.domain.point.application.spi.PointPort
import com.stack.knowledge.domain.point.domain.Point
import com.stack.knowledge.domain.solve.application.spi.SolvePort
import com.stack.knowledge.domain.solve.exception.AlreadySolvedException
import com.stack.knowledge.domain.solve.exception.SolveNotFoundException
import java.util.UUID

@UseCase
class SolveMissionUseCase(
    private val queryStudentPort: QueryStudentPort,
    private val missionPort: MissionPort,
    private val solvePort: SolvePort,
    private val securityService: SecurityService,
    private val pointPort: PointPort
) {
    fun execute(id: UUID, solveMissionRequest: SolveMissionRequest) {
        val mission = missionPort.queryMissionById(id) ?: throw MissionNotFoundException()
        val student = securityService.queryCurrentUser().let {
            queryStudentPort.queryStudentByUserId(it.id) ?: throw StudentNotFoundException()
        }

        if (mission.missionStatus != MissionStatus.OPENED)
            throw MissionNotOpenedException()

        if (solvePort.existByStudentIdAndMissionId(student.id, mission.id))
            throw AlreadySolvedException()

        val solve = Solve(
            id = UUID.randomUUID(),
            solvation = solveMissionRequest.solvation,
            solveStatus = SolveStatus.SCORING,
            student = student.id,
            mission = mission.id
        )
        val saveSolve = solvePort.save(solve) ?: throw SolveNotFoundException()

        val topPoint = ((pointPort.queryTopByMissionIdOrderByMissionPointDesc(mission.id)?.missionPoint?.times(0.97))?.toInt())

        val point = Point(
            missionPoint = topPoint ?: 1000,
            mission = mission.id,
            solve = saveSolve.id
        )
        pointPort.save(point)
    }
}