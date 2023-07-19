package com.stack.knowledege.domain.image.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.StackKnowledgeException

class ProfileImageAlreadyExist : StackKnowledgeException(ErrorCode.IMAGE_ALREADY_EXIST)