package com.stack.knowledge.domain.user.presentation.data.request

import com.stack.knowledge.domain.user.domain.constant.ApproveStatus

data class UpdateUserApproveStatusRequest(
    val approveStatus: ApproveStatus
)