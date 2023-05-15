package com.stack.knowledege.domain.user.application.spi

import com.stack.knowledege.domain.user.domain.User
import com.stack.knowledege.domain.user.domain.constant.UserRole
import java.util.UUID

interface QueryUserPort {
    fun queryCurrentUser(): User
    fun queryUserById(id: UUID): User?
    fun queryUserRoleByEmail(email: String): UserRole
    fun queryUserByEmail(email: String): User?
    fun queryExistByEmail(email: String): Boolean
}