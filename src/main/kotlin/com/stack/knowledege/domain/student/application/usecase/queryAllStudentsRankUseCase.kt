package com.stack.knowledege.domain.student.application.usecase

import com.stack.knowledege.domain.student.application.spi.StudentPort
import com.stack.knowledege.domain.student.presentation.data.response.AllStudentsRankResponse
import com.stack.knowledege.global.annotation.usecase.UseCase

@UseCase
class queryAllStudentsRankUseCase(
    private val studentPort: StudentPort
) {
    fun execute(): List<AllStudentsRankResponse> =
        studentPort.queryStudentsPointByDesc().map {
            AllStudentsRankResponse(
                id = it.id,
                email = it.user.`
            )
        }
}