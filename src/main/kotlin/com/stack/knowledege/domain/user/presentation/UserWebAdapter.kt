package com.stack.knowledege.domain.user.presentation

import com.stack.knowledege.domain.user.application.usecase.QueryAllUserRankUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserWebAdapter(
    private val queryAllUserRankUseCase: QueryAllUserRankUseCase
) {

    @GetMapping
    fun queryAllUserRank() {
        queryAllUserRankUseCase.execute()
            .let { ResponseEntity.ok(it) }
    }
}