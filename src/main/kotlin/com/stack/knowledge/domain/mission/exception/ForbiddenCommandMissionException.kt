package com.stack.knowledge.domain.mission.exception

import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.exception.StackKnowledgeException

class ForbiddenCommandMissionException : StackKnowledgeException(ErrorCode.FORBIDDEN_MISSION_COMMAND)