package com.stack.knowledege.global.security.spi

import java.util.UUID

interface SecurityPort {
    fun queryCurrentUserId(): UUID
}