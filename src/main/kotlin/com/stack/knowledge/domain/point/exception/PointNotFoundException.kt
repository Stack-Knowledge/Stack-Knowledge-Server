package com.stack.knowledge.domain.point.exception

import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.exception.StackKnowledgeException

class PointNotFoundException : StackKnowledgeException(ErrorCode.POINT_NOT_FOUND)