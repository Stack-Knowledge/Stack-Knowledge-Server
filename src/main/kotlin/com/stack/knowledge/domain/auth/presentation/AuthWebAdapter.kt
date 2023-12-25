package com.stack.knowledge.domain.auth.presentation

import com.stack.knowledge.domain.auth.application.service.*
import com.stack.knowledge.domain.auth.presentation.data.request.GAuthSignInRequest
import com.stack.knowledge.domain.auth.presentation.data.response.TokenResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthWebAdapter(
    private val gAuthSignInService: GAuthSignInService,
    private val reissueTokenService: ReissueTokenService,
    private val logoutService: LogoutService
) {
    @PostMapping
    fun signIn(@RequestBody @Valid gAuthSignInRequest: GAuthSignInRequest): ResponseEntity<TokenResponse> =
        gAuthSignInService.execute(gAuthSignInRequest)
            .let { ResponseEntity.ok(it) }

    @PatchMapping
    fun reissueToken(@RequestHeader("RefreshToken") refreshToken: String): ResponseEntity<TokenResponse> =
        reissueTokenService.execute(refreshToken)
            .let { ResponseEntity.ok(it) }

    @DeleteMapping
    fun logout(@RequestHeader("RefreshToken") refreshToken: String): ResponseEntity<Void> =
        logoutService.execute(refreshToken)
            .let { ResponseEntity.noContent().build() }
}