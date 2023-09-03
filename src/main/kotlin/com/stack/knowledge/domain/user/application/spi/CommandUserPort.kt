package com.stack.knowledge.domain.user.application.spi

import com.stack.knowledge.domain.user.domain.User

interface CommandUserPort {
    fun save(user: User): User
}