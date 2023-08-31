package com.stack.knowledge.domain.auth.exception

import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.exception.StackKnowledgeException

class RefreshTokenNotFoundException : StackKnowledgeException(ErrorCode.REFRESH_TOKEN_NOT_FOUND)