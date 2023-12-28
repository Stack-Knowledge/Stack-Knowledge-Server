package com.stack.knowledge.domain.user.application.service

import com.stack.knowledge.common.annotation.usecase.UseCase
import com.stack.knowledge.domain.user.application.spi.UserPort
import com.stack.knowledge.domain.user.domain.constant.ApproveStatus
import com.stack.knowledge.domain.user.exception.AlreadyApprovedUserException
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import com.stack.knowledge.domain.user.presentation.data.request.UpdateUserApproveStatusRequest
import java.util.*

@UseCase
class UpdateUserApproveStatusService(
    private val userPort: UserPort
) {
    fun execute(userId: UUID, updateUserApproveStatusRequest: UpdateUserApproveStatusRequest) {
        val user = userPort.queryUserById(userId) ?: throw UserNotFoundException()

        if (user.approveStatus == ApproveStatus.APPROVED)
            throw AlreadyApprovedUserException()

        when (updateUserApproveStatusRequest.approveStatus) {
            ApproveStatus.PENDING -> userPort.deleteByUserId(userId)
            ApproveStatus.APPROVED -> userPort.save(user.copy(approveStatus = updateUserApproveStatusRequest.approveStatus))
        }
    }
}