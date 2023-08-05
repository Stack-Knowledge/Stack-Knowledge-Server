package com.stack.knowledege.common.spi

import java.util.UUID

interface SecurityPort {
    fun queryCurrentUserId(): UUID
    fun queryCurrentUserAuthority(): String
}