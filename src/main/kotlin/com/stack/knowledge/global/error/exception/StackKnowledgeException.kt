package com.stack.knowledge.global.error.exception

import com.stack.knowledge.global.error.ErrorCode

open class StackKnowledgeException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)