package com.stack.knowledge.thirdparty.feign.exception

import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.exception.StackKnowledgeException

class UnAuthorizedException : StackKnowledgeException(ErrorCode.UNAUTHORIZED) {
}