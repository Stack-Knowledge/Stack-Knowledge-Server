package com.stack.knowledge.domain.time.exception

import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.exception.StackKnowledgeException

class TimeLimitExceededException : StackKnowledgeException(ErrorCode.TIME_LIMIT_EXCEEDED)