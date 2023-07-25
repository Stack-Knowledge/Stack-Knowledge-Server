package com.stack.knowledege.domain.user.presentation

import com.stack.knowledege.domain.user.application.usecase.QueryScoringPageUseCase
import com.stack.knowledege.domain.user.presentation.data.response.AllScoringResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserWebAdapter(
    private val queryScoringPageUseCase: QueryScoringPageUseCase
) {
    @GetMapping("/scoring")
    fun queryAllSolve(): ResponseEntity<List<AllScoringResponse>> =
        queryScoringPageUseCase.execute()
            .let { ResponseEntity.ok(it) }
}