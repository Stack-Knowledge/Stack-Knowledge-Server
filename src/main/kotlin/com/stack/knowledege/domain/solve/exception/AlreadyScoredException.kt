package com.stack.knowledege.domain.solve.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.BasicException

class AlreadyScoredException : BasicException(ErrorCode.ALREADY_SCORED)