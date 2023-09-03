package com.stack.knowledge.domain.solve.exception

import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.exception.StackKnowledgeException

class StudentOnlyException : StackKnowledgeException(ErrorCode.ONLY_STUDENT)