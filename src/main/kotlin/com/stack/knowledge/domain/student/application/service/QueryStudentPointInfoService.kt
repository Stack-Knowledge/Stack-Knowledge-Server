package com.stack.knowledge.domain.student.application.service

import com.stack.knowledge.common.annotation.service.ServiceWithReadOnlyTransaction
import com.stack.knowledge.common.service.SecurityService
import com.stack.knowledge.domain.student.application.spi.QueryStudentPort
import com.stack.knowledge.domain.student.presentation.data.response.StudentPointResponse
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import com.stack.knowledge.domain.user.presentation.data.response.UserResponse

@ServiceWithReadOnlyTransaction
class QueryStudentPointInfoService(
    private val queryStudentPort: QueryStudentPort,
    private val securityService: SecurityService
) {
    fun execute(): StudentPointResponse {
        val user = securityService.queryCurrentUser()
        val student = queryStudentPort.queryStudentByUserId(user.id) ?: throw UserNotFoundException()
        val rank = queryStudentPort.queryStudentRankByStudentId(student.id)

        return StudentPointResponse(
            id = student.id,
            currentPoint = student.currentPoint,
            cumulatePoint = student.cumulatePoint,
            ranking = rank,
            user = UserResponse(
                id = user.id,
                name = user.name,
                profileImage = user.profileImage
            )
        )
    }
}