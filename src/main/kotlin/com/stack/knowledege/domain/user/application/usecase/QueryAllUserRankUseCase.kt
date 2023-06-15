package com.stack.knowledege.domain.user.application.usecase

import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.domain.user.presentation.data.response.AllUserRankResponse
import com.stack.knowledege.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryAllUserRankUseCase(
    private val queryUserPort: QueryUserPort,
) {
    fun execute(): List<AllUserRankResponse> =
        queryUserPort.queryAllUser().map {
            AllUserRankResponse(
                id = it.id,
                email = it.email,
                name = it.name,
                profileImage = it.profileImage
            )
        }
}