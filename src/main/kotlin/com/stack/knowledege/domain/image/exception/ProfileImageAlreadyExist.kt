package com.stack.knowledege.domain.image.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.BasicException

class ProfileImageAlreadyExist : BasicException(ErrorCode.IMAGE_ALREADY_EXIST)