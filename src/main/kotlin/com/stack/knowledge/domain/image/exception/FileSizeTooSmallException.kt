package com.stack.knowledge.domain.image.exception

import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.exception.StackKnowledgeException

class FileSizeTooSmallException : StackKnowledgeException(ErrorCode.FILE_SIZE_TOO_SMALL)