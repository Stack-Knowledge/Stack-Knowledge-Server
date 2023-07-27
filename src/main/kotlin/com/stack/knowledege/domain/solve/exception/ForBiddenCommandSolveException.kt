package com.stack.knowledege.domain.solve.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.StackKnowledgeException

class ForBiddenCommandSolveException : StackKnowledgeException(ErrorCode.FORBIDDEN_SOLVE_COMMAND)