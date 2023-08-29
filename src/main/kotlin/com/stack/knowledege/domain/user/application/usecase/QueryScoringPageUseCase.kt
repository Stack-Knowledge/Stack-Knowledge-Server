package com.stack.knowledege.domain.user.application.usecase

import com.stack.knowledege.domain.solve.application.spi.QuerySolvePort
import com.stack.knowledege.domain.user.presentation.data.response.AllScoringResponse
import com.stack.knowledege.common.annotation.usecase.ReadOnlyUseCase
import com.stack.knowledege.domain.point.application.spi.QueryPointPort
import com.stack.knowledege.domain.solve.domain.constant.SolveStatus
import com.stack.knowledege.domain.student.application.spi.QueryStudentPort
import com.stack.knowledege.domain.student.exception.StudentNotFoundException
import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.domain.user.presentation.data.response.UserResponse

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
            val point = queryPointPort.

            AllScoringResponse(
                solveId = it.id,
                solveStatus = it.solveStatus,
                point = 1,
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