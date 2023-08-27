package com.stack.knowledege.domain.order.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.StackKnowledgeException

class LackOrderException : StackKnowledgeException(ErrorCode.LACK_ORDER)