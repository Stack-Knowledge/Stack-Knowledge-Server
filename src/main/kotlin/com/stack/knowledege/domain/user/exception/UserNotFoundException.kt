package com.stack.knowledege.domain.user.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.StackKnowledgeException

class UserNotFoundException : StackKnowledgeException(ErrorCode.USER_NOT_FOUND)