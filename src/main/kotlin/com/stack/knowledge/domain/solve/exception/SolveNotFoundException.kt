package com.stack.knowledge.domain.solve.exception

import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.exception.StackKnowledgeException

class SolveNotFoundException : StackKnowledgeException(ErrorCode.SOLVE_NOT_FOUND)