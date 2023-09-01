package com.stack.knowledge.common.service

import com.stack.knowledge.domain.user.domain.User

interface SecurityService {
    fun queryCurrentUser(): User
    fun queryCurrentUserAuthority(): String
}