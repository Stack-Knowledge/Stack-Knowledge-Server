package com.stack.knowledege.domain.auth.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.StackKnowledgeException

class RefreshTokenNotFoundException : StackKnowledgeException(ErrorCode.REFRESH_TOKEN_NOT_FOUND)