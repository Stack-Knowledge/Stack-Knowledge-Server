package com.stack.knowledege.domain.user.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.BasicException

class UserNotFoundException : BasicException(ErrorCode.USER_NOT_FOUND)