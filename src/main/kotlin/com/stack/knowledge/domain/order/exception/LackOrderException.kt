package com.stack.knowledge.domain.order.exception

import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.exception.StackKnowledgeException

class LackOrderException : StackKnowledgeException(ErrorCode.LACK_ORDER)