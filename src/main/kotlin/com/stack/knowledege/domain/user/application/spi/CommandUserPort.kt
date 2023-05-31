package com.stack.knowledege.domain.user.application.spi

import com.stack.knowledege.domain.user.domain.User

interface CommandUserPort {
    fun save(user: User): User
}