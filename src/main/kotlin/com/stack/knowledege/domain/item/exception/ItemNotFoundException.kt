package com.stack.knowledege.domain.item.exception

import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.exception.BasicException

class ItemNotFoundException : BasicException(ErrorCode.ITEM_NOT_FOUND)