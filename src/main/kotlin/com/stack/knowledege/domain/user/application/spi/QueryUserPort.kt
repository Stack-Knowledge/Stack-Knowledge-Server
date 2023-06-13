package com.stack.knowledege.domain.user.application.spi

import com.stack.knowledege.domain.user.domain.User
import com.stack.knowledege.domain.user.domain.constant.UserRole
import java.util.UUID

interface QueryUserPort {
    fun queryUserById(id: UUID): User?
    fun queryUserRoleByEmail(email: String, role: String): UserRole
    fun queryUserByEmail(email: String): User?
    fun queryExistByEmail(email: String): Boolean
    fun queryCurrentUser(): User
    fun queryAllUser(): List<User>
    fun queryAllUserPointDesc(): List<Int>
}