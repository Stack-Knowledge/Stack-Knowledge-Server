package com.stack.knowledege.domain.order.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.StackKnowledgeException

class AlreadyCompletedOrderException : StackKnowledgeException(ErrorCode.ALREADY_COMPLETED_ORDER)