package com.stack.knowledege.global.security.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.StackKnowledgeException

class ForbiddenException : StackKnowledgeException(ErrorCode.FORBIDDEN)