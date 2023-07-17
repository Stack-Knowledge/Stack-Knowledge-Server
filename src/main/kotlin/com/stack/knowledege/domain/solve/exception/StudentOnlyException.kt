package com.stack.knowledege.domain.solve.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.StackKnowledgeException

class StudentOnlyException : StackKnowledgeException(ErrorCode.ONLY_STUDENT)