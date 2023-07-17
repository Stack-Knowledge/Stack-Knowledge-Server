package com.stack.knowledege.domain.item.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.StackKnowledgeException

class ItemNotFoundException : StackKnowledgeException(ErrorCode.ITEM_NOT_FOUND)