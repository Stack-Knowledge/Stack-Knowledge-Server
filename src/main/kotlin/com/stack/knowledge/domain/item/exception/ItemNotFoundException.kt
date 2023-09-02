package com.stack.knowledge.domain.item.exception

import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.exception.StackKnowledgeException

class ItemNotFoundException : StackKnowledgeException(ErrorCode.ITEM_NOT_FOUND)