package com.stack.knowledge.domain.user.exception

import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.exception.StackKnowledgeException

class UserNotFoundException : StackKnowledgeException(ErrorCode.USER_NOT_FOUND)