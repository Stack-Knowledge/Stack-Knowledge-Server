package com.stack.knowledege.common.service

import java.util.*

interface SecurityService {
    fun queryCurrentUserId(): UUID
    fun queryCurrentUserAuthority(): String
}