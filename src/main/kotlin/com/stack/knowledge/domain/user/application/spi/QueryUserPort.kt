package com.stack.knowledge.domain.user.application.spi

import com.stack.knowledge.domain.user.domain.User
import com.stack.knowledge.domain.user.domain.constant.Authority
import java.util.UUID

interface QueryUserPort {
    fun queryUserById(id: UUID): User?
    fun queryUserRoleByEmail(email: String, authority: String): Authority
    fun queryUserByEmail(email: String): User?
    fun queryExistByEmail(email: String): Boolean
    fun queryAllUser(): List<User>
}