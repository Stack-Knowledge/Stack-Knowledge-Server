package com.stack.knowledge.domain.student.exception

import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.exception.StackKnowledgeException

class StudentNotFoundException : StackKnowledgeException(ErrorCode.STUDENT_NOT_FOUND)