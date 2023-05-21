package com.stack.knowledege.domain.user.application.usecase

import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.domain.user.persistence.repository.UserRepository
import com.stack.knowledege.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryUserRankUseCase(
    private val queryUserPort: QueryUserPort,
    private val userRepository: UserRepository
) {
    fun execute(): List<Int> {
        val user = queryUserPort.queryCurrentUser()
        val point = queryUserPort.queryUserPointByEmail(user.email)
        return point
    }
}