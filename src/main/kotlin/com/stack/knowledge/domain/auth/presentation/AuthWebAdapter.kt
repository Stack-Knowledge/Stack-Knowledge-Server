package com.stack.knowledge.domain.auth.presentation

import com.stack.knowledge.domain.auth.application.usecase.GAuthSignInUseCase
import com.stack.knowledge.domain.auth.application.usecase.LogoutUseCase
import com.stack.knowledge.domain.auth.application.usecase.ReissueTokenUseCase
import com.stack.knowledge.domain.auth.presentation.data.request.GAuthSignInRequest
import com.stack.knowledge.domain.auth.presentation.data.response.TokenResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthWebAdapter(
    private val gAuthSignInUseCase: GAuthSignInUseCase,
    private val reissueTokenUseCase: ReissueTokenUseCase,
    private val logoutUseCase: LogoutUseCase
) {
    @PostMapping
    fun signIn(@RequestBody @Valid gAuthSignInRequest: GAuthSignInRequest): ResponseEntity<TokenResponse> =
        gAuthSignInUseCase.execute(gAuthSignInRequest)
            .let { ResponseEntity.ok(it) }

    @PatchMapping
    fun reissueToken(@RequestHeader("RefreshToken") refreshToken: String): ResponseEntity<TokenResponse> =
        reissueTokenUseCase.execute(refreshToken)
            .let { ResponseEntity.ok(it) }

    @DeleteMapping
    fun logout(@RequestHeader("RefreshToken") refreshToken: String): ResponseEntity<Void> =
        logoutUseCase.execute(refreshToken)
            .let { ResponseEntity.noContent().build() }
}