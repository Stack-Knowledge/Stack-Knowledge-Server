package com.stack.knowledege.common.service

import com.stack.knowledege.domain.user.domain.User

interface SecurityService {
    fun queryCurrentUser(): User
}