package com.stack.knowledege.domain.order.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.StackKnowledgeException

class OrderNotFoundException : StackKnowledgeException(ErrorCode.ORDER_NOT_FOUND)