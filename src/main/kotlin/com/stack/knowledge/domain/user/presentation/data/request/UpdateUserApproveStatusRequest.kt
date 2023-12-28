package com.stack.knowledge.domain.user.presentation.data.request

import com.stack.knowledge.domain.user.domain.constant.ApproveStatus
import javax.persistence.EnumType
import javax.persistence.Enumerated

data class UpdateUserApproveStatusRequest(
    @Enumerated(EnumType.STRING)
    val approveStatus: ApproveStatus
)