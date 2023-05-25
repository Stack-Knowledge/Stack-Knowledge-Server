package com.stack.knowledege.domain.mission.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.BasicException

class NotFoundMissionException : BasicException(ErrorCode.MISSION_NOT_FOUND)