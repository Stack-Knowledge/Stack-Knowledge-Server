package com.stack.knowledege.global.error.exception

import com.stack.knowledege.global.error.ErrorCode

open class BasicException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)