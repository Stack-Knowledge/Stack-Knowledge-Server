package com.stack.knowledge.domain.user.application.usecase

import com.stack.knowledge.domain.solve.application.spi.QuerySolvePort
import com.stack.knowledge.domain.user.presentation.data.response.AllScoringResponse
import com.stack.knowledge.common.annotation.usecase.ReadOnlyUseCase
import com.stack.knowledge.domain.solve.domain.constant.SolveStatus
import com.stack.knowledge.domain.point.application.spi.QueryPointPort
import com.stack.knowledge.domain.point.exception.PointNotFoundException
import com.stack.knowledge.domain.student.application.spi.QueryStudentPort
import com.stack.knowledge.domain.student.exception.StudentNotFoundException
import com.stack.knowledge.domain.user.application.spi.QueryUserPort
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import com.stack.knowledge.domain.user.presentation.data.response.UserResponse

@ReadOnlyUseCase
class QueryScoringPageUseCase(
    private val querySolvePort: QuerySolvePort,
    private val queryStudentPort: QueryStudentPort,
    private val queryUserPort: QueryUserPort,
    private val queryPointPort: QueryPointPort
) {
    fun execute(): List<AllScoringResponse> {
        val solves = querySolvePort.queryAllSolveBySolveStatus(SolveStatus.SCORING)

        return solves.map {
            val student = queryStudentPort.queryStudentById(it.student) ?: throw StudentNotFoundException()
            val user = queryUserPort.queryUserById(student.user) ?: throw UserNotFoundException()
            val point = queryPointPort.queryPointBySolve(it) ?: throw PointNotFoundException()

            AllScoringResponse(
                solveId = it.id,
                solveStatus = it.solveStatus,
                point = point.missionPoint,
                user = UserResponse(
                    id = user.id,
                    email = user.email,
                    name = user.name,
                    profileImage = user.profileImage
                )
            )
        }
    }
}