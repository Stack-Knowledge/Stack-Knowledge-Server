package com.stack.knowledege.domain.solve.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.BasicException

class StudentOnlyException : BasicException(ErrorCode.ONLY_STUDENT_EXCEPTION)