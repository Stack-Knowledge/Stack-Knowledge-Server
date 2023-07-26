package com.stack.knowledege.domain.auth.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.StackKnowledgeException

class InvalidRefreshTokenException : StackKnowledgeException(ErrorCode.INVALID_TOKEN)