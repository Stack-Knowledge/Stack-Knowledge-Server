package com.stack.knowledge.domain.user.exception

import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.exception.StackKnowledgeException

class InvalidApproveStatusException : StackKnowledgeException(ErrorCode.INVALID_APPROVE_STATUS)