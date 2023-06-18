package com.stack.knowledege.domain.student.application.usecase

import com.stack.knowledege.domain.student.application.spi.StudentPort
import com.stack.knowledege.domain.student.presentation.data.response.AllStudentsRankResponse
import com.stack.knowledege.domain.user.application.spi.UserPort
import com.stack.knowledege.domain.user.presentation.data.response.UserResponse
import com.stack.knowledege.global.annotation.usecase.UseCase

@UseCase
class queryAllStudentsRankUseCase(
    private val userPort: UserPort,
    private val studentPort: StudentPort
) {
    fun execute(): List<AllStudentsRankResponse> {
        return userPort.queryAllUser().map { user ->
            return studentPort.queryStudentsPointDesc().map {
                AllStudentsRankResponse(
                    id = it.id,
                    point = it.point,
                    user = UserResponse(
                        id = it.user,
                        email = user.email,
                        name = user.name,
                        profileImage = user.profileImage
                    )
                )
            }
        }
    }
}