package com.stack.knowledge.domain.auth.exception

import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.exception.StackKnowledgeException

class NotApprovedUserException : StackKnowledgeException(ErrorCode.NOT_APPROVED_USER)