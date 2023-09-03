package com.stack.knowledge.domain.solve.exception

import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.exception.StackKnowledgeException

class ForBiddenCommandSolveException : StackKnowledgeException(ErrorCode.FORBIDDEN_SOLVE_COMMAND)