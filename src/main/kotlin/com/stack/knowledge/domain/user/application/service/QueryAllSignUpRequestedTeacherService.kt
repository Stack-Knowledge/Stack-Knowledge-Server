package com.stack.knowledge.domain.user.application.service

import com.stack.knowledge.common.annotation.usecase.ReadOnlyUseCase
import com.stack.knowledge.domain.user.application.spi.QueryUserPort
import com.stack.knowledge.domain.user.domain.constant.ApproveStatus
import com.stack.knowledge.domain.user.presentation.data.response.AllSignUpRequestTeacherResponse

@ReadOnlyUseCase
class QueryAllSignUpRequestedTeacherService(
    private val queryUserPort: QueryUserPort
) {
    fun execute(): List<AllSignUpRequestTeacherResponse> {
        val users = queryUserPort.queryAllUserByApproveStatus(ApproveStatus.PENDING)

        return users.map {
            AllSignUpRequestTeacherResponse(
                name = it.name,
                createdAt = it.createdAt
            )
        }
    }
}