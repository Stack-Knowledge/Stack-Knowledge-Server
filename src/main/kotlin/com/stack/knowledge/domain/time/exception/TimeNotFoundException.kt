package com.stack.knowledge.domain.time.exception

import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.exception.StackKnowledgeException

class TimeNotFoundException : StackKnowledgeException(ErrorCode.TIME_NOT_FOUND)