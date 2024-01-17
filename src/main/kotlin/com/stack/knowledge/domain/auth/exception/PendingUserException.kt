package com.stack.knowledge.domain.auth.exception

import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.exception.StackKnowledgeException

class PendingUserException : StackKnowledgeException(ErrorCode.PENDING_USER)