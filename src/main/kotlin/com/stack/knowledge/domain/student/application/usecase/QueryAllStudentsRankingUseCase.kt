package com.stack.knowledge.domain.student.application.usecase

import com.stack.knowledge.common.annotation.usecase.ReadOnlyUseCase
import com.stack.knowledge.domain.student.application.spi.QueryStudentPort
import com.stack.knowledge.domain.student.presentation.data.response.AllStudentsRankingResponse
import com.stack.knowledge.domain.user.application.spi.QueryUserPort
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import com.stack.knowledge.domain.user.presentation.data.response.UserResponse

@ReadOnlyUseCase
class QueryAllStudentsRankingUseCase(
    private val queryUserPort: QueryUserPort,
    private val queryStudentPort: QueryStudentPort
) {
    fun execute(): List<AllStudentsRankingResponse> =
        queryStudentPort.queryAllStudentsOrderByCumulatePointDesc().map {
            val user = queryUserPort.queryUserById(it.user) ?: throw UserNotFoundException()

            AllStudentsRankingResponse(
                id = it.id,
                cumulatePoint = it.cumulatePoint,
                user = UserResponse(
                    id = it.user,
                    name = user.name,
                    profileImage = user.profileImage
                )
            )
        }
}