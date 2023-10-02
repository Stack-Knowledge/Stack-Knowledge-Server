package com.stack.knowledge.domain.user.exception

import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.exception.StackKnowledgeException

class ProfileImageNotFoundException : StackKnowledgeException(ErrorCode.PROFILE_IMAGE_NOT_FOUND)