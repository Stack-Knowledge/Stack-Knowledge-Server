package com.stack.knowledge.domain.user.application.usecase

import com.stack.knowledge.common.annotation.usecase.ReadOnlyUseCase
import com.stack.knowledge.common.spi.SecurityPort
import com.stack.knowledge.domain.mission.application.spi.QueryMissionPort
import com.stack.knowledge.domain.mission.exception.MissionNotFoundException
import com.stack.knowledge.domain.point.application.spi.QueryPointPort
import com.stack.knowledge.domain.point.exception.PointNotFoundException
import com.stack.knowledge.domain.solve.application.spi.QuerySolvePort
import com.stack.knowledge.domain.solve.domain.constant.SolveStatus
import com.stack.knowledge.domain.student.application.spi.QueryStudentPort
import com.stack.knowledge.domain.student.exception.StudentNotFoundException
import com.stack.knowledge.domain.user.application.spi.QueryUserPort
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import com.stack.knowledge.domain.user.presentation.data.response.AllScoringResponse
import com.stack.knowledge.domain.user.presentation.data.response.UserResponse

@ReadOnlyUseCase
class QueryScoringPageUseCase(
    private val querySolvePort: QuerySolvePort,
    private val queryUserPort: QueryUserPort,
    private val queryPointPort: QueryPointPort,
    private val queryMissionPort: QueryMissionPort,
    private val securityPort: SecurityPort,
    private val queryStudentPort: QueryStudentPort
) {
    fun execute(): List<AllScoringResponse> {
        val userId = securityPort.queryCurrentUserId()

        return queryMissionPort.queryAllMissionByUserId(userId).flatMap {
            querySolvePort.queryAllSolveBySolveStatusAndMission(SolveStatus.SCORING, it).map { solve ->
                val point = queryPointPort.queryPointBySolve(solve) ?: throw PointNotFoundException()
                val mission = queryMissionPort.queryMissionById(solve.mission) ?: throw MissionNotFoundException()
                val student = queryStudentPort.queryStudentById(solve.student) ?: throw StudentNotFoundException()
                val user = queryUserPort.queryUserById(student.user) ?: throw UserNotFoundException()

                AllScoringResponse(
                    solveId = solve.id,
                    solveStatus = solve.solveStatus,
                    title = mission.title,
                    point = point.missionPoint,
                    user = UserResponse(
                        id = mission.id,
                        email = user.email,
                        name = user.name,
                        profileImage = user.profileImage
                    )
                )
            }
        }
    }
}