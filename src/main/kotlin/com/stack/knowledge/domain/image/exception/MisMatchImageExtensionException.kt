package com.stack.knowledge.domain.image.exception

import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.exception.StackKnowledgeException

class MisMatchImageExtensionException : StackKnowledgeException(ErrorCode.MISMATCH_IMAGE_EXTENSION)