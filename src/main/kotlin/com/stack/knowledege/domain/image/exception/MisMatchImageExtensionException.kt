package com.stack.knowledege.domain.image.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.StackKnowledgeException

class MisMatchImageExtensionException : StackKnowledgeException(ErrorCode.MISMATCH_IMAGE_EXTENSION)