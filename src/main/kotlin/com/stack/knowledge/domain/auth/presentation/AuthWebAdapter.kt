package com.stack.knowledge.domain.auth.presentation

import com.stack.knowledge.domain.auth.application.usecase.GAuthSignInUseCase
import com.stack.knowledge.domain.auth.application.usecase.ReissueTokenUseCase
import com.stack.knowledge.domain.auth.presentation.data.request.GAuthSignInRequest
import com.stack.knowledge.domain.auth.presentation.data.response.TokenResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthWebAdapter(
    private val gAuthSignInUseCase: GAuthSignInUseCase,
    private val reissueTokenUseCase: ReissueTokenUseCase
) {
    @PostMapping
    fun signIn(@RequestBody gAuthSignInRequest: GAuthSignInRequest): ResponseEntity<TokenResponse> =
        gAuthSignInUseCase.execute(gAuthSignInRequest)
            .let { ResponseEntity.ok(it) }

    @PatchMapping
    fun reissueToken(@RequestHeader("RefreshToken") refreshToken: String): ResponseEntity<TokenResponse> =
        reissueTokenUseCase.execute(refreshToken)
            .let { ResponseEntity.ok(it) }
}