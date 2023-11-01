package com.stack.knowledge.common.service

import com.stack.knowledge.domain.user.domain.User
import java.util.UUID

interface SecurityService {
    fun queryCurrentUser(): User
    fun queryCurrentUserAuthority(): String
    fun queryCurrentUserId(): UUID
}