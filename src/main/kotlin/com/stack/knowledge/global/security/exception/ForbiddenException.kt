package com.stack.knowledge.global.security.exception

import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.exception.StackKnowledgeException

class ForbiddenException : StackKnowledgeException(ErrorCode.FORBIDDEN)