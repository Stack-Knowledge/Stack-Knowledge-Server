package com.stack.knowledge.domain.user.application.service

import com.stack.knowledge.common.annotation.usecase.ReadOnlyUseCase
import com.stack.knowledge.common.service.SecurityService
import com.stack.knowledge.domain.mission.application.spi.QueryMissionPort
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
class QueryScoringPageService(
    private val querySolvePort: QuerySolvePort,
    private val queryUserPort: QueryUserPort,
    private val queryPointPort: QueryPointPort,
    private val queryMissionPort: QueryMissionPort,
    private val securityService: SecurityService,
    private val queryStudentPort: QueryStudentPort
) {
    fun execute(): List<AllScoringResponse> {
        val userId = securityService.queryCurrentUserId()

        return queryMissionPort.queryAllMissionsByUserIdOrderByCreatedAtDesc(userId).flatMap {
            querySolvePort.queryAllSolveBySolveStatusAndMissionOrderByCreatedAtDesc(SolveStatus.SCORING, it).map { solve ->
                val point = queryPointPort.queryPointBySolve(solve) ?: throw PointNotFoundException()
                val student = queryStudentPort.queryStudentById(solve.student) ?: throw StudentNotFoundException()
                val user = queryUserPort.queryUserById(student.user) ?: throw UserNotFoundException()

                AllScoringResponse(
                    solveId = solve.id,
                    solveStatus = solve.solveStatus,
                    title = it.title,
                    point = point.missionPoint,
                    user = UserResponse(
                        id = user.id,
                        name = user.name,
                        profileImage = user.profileImage
                    )
                )
            }
        }
    }
}