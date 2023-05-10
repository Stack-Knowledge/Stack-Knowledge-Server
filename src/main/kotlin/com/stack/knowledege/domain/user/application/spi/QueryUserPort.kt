package com.stack.knowledege.domain.user.application.spi

import com.stack.knowledege.domain.user.domain.User
import java.util.UUID

interface QueryUserPort {
    fun queryCurrentUser(): User

    fun queryUserById(id: UUID): User?
}