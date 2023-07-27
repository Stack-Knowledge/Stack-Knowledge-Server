package com.stack.knowledege.domain.solve.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.StackKnowledgeException

class UnsupportedSolveStatusException : StackKnowledgeException(ErrorCode.UNSUPPORTED_SOLVE_STATUS)