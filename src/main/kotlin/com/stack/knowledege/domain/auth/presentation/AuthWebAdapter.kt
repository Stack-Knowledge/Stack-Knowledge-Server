package com.stack.knowledege.domain.auth.presentation

import com.stack.knowledege.domain.auth.application.usecase.GAuthSignInUseCase
import com.stack.knowledege.domain.auth.presentation.data.response.TokenResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthWebAdapter(
    private val gAuthSignInUseCase: GAuthSignInUseCase
) {
    @PostMapping
    fun signIn(@RequestBody code: String): ResponseEntity<TokenResponse> =
        gAuthSignInUseCase.execute(code)
            .let { ResponseEntity.ok(it) }
}