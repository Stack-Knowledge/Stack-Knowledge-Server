package com.stack.knowledge.domain.solve.exception

import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.exception.StackKnowledgeException

class AlreadyScoredException : StackKnowledgeException(ErrorCode.ALREADY_SCORED)