package com.stack.knowledege.domain.mission.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.StackKnowledgeException

class AlreadySolvedMissionException : StackKnowledgeException(ErrorCode.ALREADY_SOLVED_MISSION)