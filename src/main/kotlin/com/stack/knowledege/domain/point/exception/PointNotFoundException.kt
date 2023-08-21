package com.stack.knowledege.domain.point.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.StackKnowledgeException

class PointNotFoundException : StackKnowledgeException(ErrorCode.POINT_NOT_FOUND)