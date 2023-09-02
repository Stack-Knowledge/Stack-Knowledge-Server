package com.stack.knowledge.domain.student.application.usecase

import com.stack.knowledge.common.annotation.usecase.ReadOnlyUseCase
import com.stack.knowledge.domain.student.application.spi.QueryStudentPort
import com.stack.knowledge.domain.student.presentation.data.response.AllStudentsRankingResponse
import com.stack.knowledge.domain.user.application.spi.QueryUserPort
import com.stack.knowledge.domain.user.presentation.data.response.UserResponse

@ReadOnlyUseCase
class QueryAllStudentsRankingUseCase(
    private val queryUserPort: QueryUserPort,
    private val queryStudentPort: QueryStudentPort
) {
    fun execute(): List<AllStudentsRankingResponse> {
        val response = queryUserPort.queryAllUser().map { user ->
            return queryStudentPort.queryStudentsPointDesc().map {
                AllStudentsRankingResponse(
                    id = it.id,
                    cumulatePoint = it.cumulatePoint,
                    user = UserResponse(
                        id = it.user!!,
                        email = user.email,
                        name = user.name,
                        profileImage = user.profileImage
                    )
                )
            }
        }
        return response
    }
}