package com.stack.knowledge.domain.student.application.usecase

import com.stack.knowledge.common.annotation.usecase.ReadOnlyUseCase
import com.stack.knowledge.common.service.SecurityService
import com.stack.knowledge.domain.student.application.spi.QueryStudentPort
import com.stack.knowledge.domain.student.presentation.data.response.StudentPointResponse
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import com.stack.knowledge.domain.user.presentation.data.response.UserResponse

@ReadOnlyUseCase
class QueryStudentPointInfoUseCase(
    private val queryStudentPort: QueryStudentPort,
    private val securityService: SecurityService
) {
    fun execute(): StudentPointResponse {
        val user = securityService.queryCurrentUser()
        val student = queryStudentPort.queryStudentByUserId(user.id) ?: throw UserNotFoundException()

        return StudentPointResponse(
            id = student.id,
            currentPoint = student.currentPoint,
            cumulatePoint = student.cumulatePoint,
            user = UserResponse(
                id = user.id,
                name = user.name,
                profileImage = user.profileImage
            )
        )
    }
}