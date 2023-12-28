package com.stack.knowledge.domain.user.application.service

import com.stack.knowledge.common.annotation.usecase.ReadOnlyUseCase
import com.stack.knowledge.domain.user.application.spi.QueryUserPort
import com.stack.knowledge.domain.user.domain.constant.ApproveStatus
import com.stack.knowledge.domain.user.presentation.data.response.AllSignInRequestTeacherResponse

@ReadOnlyUseCase
class QueryAllSignInRequestedTeacherService(
    private val queryUserPort: QueryUserPort
) {
    fun execute(): List<AllSignInRequestTeacherResponse> {
        val users = queryUserPort.queryAllUserByApproveStatus(ApproveStatus.PENDING)

        return users.map {
            AllSignInRequestTeacherResponse(
                name = it.name,
                createdAt = it.createdAt
            )
        }
    }
}