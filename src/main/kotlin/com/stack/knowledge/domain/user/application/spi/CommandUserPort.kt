package com.stack.knowledge.domain.user.application.spi

import com.stack.knowledge.domain.user.domain.User
import java.util.*

interface CommandUserPort {
    fun save(user: User): User
    fun deleteByUserId(userId: UUID)
}