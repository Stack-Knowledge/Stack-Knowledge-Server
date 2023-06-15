package com.stack.knowledege.domain.image.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.BasicException

class MisMatchImageExtensionException : BasicException(ErrorCode.MISMATCH_IMAGE_EXTENSION)