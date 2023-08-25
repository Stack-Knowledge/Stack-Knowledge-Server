package com.stack.knowledege.domain.student.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.StackKnowledgeException

class StudentNotFoundException : StackKnowledgeException(ErrorCode.STUDENT_NOT_FOUND)