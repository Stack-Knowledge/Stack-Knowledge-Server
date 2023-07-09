package com.stack.knowledege.domain.student.application.usecase

import com.stack.knowledege.domain.student.application.spi.QueryStudentPort
import com.stack.knowledege.domain.student.presentation.data.response.AllStudentsRankResponse
import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.domain.user.presentation.data.response.UserResponse
import com.stack.knowledege.global.annotation.usecase.UseCase

@UseCase
class queryAllStudentsRankingUseCase(
    private val queryUserPort: QueryUserPort,
    private val queryStudentPort: QueryStudentPort
) {
    fun execute(): List<AllStudentsRankResponse> {
         val response = queryUserPort.queryAllUser().map { user ->
             return queryStudentPort.queryStudentsPointDesc().map {
                AllStudentsRankResponse(
                    id = it.id,
                    point = it.point,
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