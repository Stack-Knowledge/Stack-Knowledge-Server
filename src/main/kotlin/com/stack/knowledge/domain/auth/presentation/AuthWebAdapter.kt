package com.stack.knowledge.domain.auth.presentation

import com.stack.knowledge.domain.auth.application.service.*
import com.stack.knowledge.domain.auth.presentation.data.request.GoogleStudentSignInRequest
import com.stack.knowledge.domain.auth.presentation.data.request.GoogleTeacherSignInRequest
import com.stack.knowledge.domain.auth.presentation.data.response.TokenResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthWebAdapter(
    private val reissueTokenService: ReissueTokenService,
    private val logoutService: LogoutService,
    private val googleTeacherSignInService: GoogleTeacherSignInService,
    private val googleStudentSignInService: GoogleStudentSignInService
) {

    @PostMapping("/teacher")
    fun googleTeacherSignIn(@RequestBody @Valid googleTeacherSignInRequest: GoogleTeacherSignInRequest): ResponseEntity<TokenResponse> =
        googleTeacherSignInService.execute(googleTeacherSignInRequest)
            .let { ResponseEntity.ok(it) }

    @PostMapping("/teacher/cookie")
    fun googleTeacherSignInCookie(@RequestBody @Valid googleTeacherSignInRequest: GoogleTeacherSignInRequest, response: HttpServletResponse): ResponseEntity<TokenResponse> {
        val serviceResponse = googleTeacherSignInService.execute(googleTeacherSignInRequest)

        val accessToken = Cookie("access_token", serviceResponse.accessToken).apply {
            path = "/"
            isHttpOnly = true
            maxAge = 60 * 60 // 1시간
        }

        val refreshToken = Cookie("refresh_token", serviceResponse.refreshToken).apply {
            path = "/"
            isHttpOnly = true
            maxAge = 60 * 60 * 24 * 30 // 30일
        }

        val expiredAt = Cookie("expired_at", serviceResponse.expiredAt.toString()).apply {
            path = "/"
            isHttpOnly = true
            maxAge = 60 * 60 // 1시간
        }

        val authority = Cookie("authority", serviceResponse.authority.toString()).apply {
            path = "/"
            isHttpOnly = true
            maxAge = 60 * 60 // 1시간
        }

        response.addCookie(accessToken)
        response.addCookie(refreshToken)
        response.addCookie(expiredAt)
        response.addCookie(authority)

        return ResponseEntity.ok().build()
    }

    @PostMapping("/student")
    fun googleStudentSignIn(@RequestBody @Valid googleStudentSignInRequest: GoogleStudentSignInRequest): ResponseEntity<TokenResponse> =
        googleStudentSignInService.execute(googleStudentSignInRequest)
            .let { ResponseEntity.ok(it) }

    @PostMapping("/student/cookie")
    fun googleStudentSignInCookie(@RequestBody @Valid googleStudentSignInRequest: GoogleStudentSignInRequest, response: HttpServletResponse): ResponseEntity<TokenResponse> {
        val serviceResponse = googleStudentSignInService.execute(googleStudentSignInRequest)

        val accessToken = Cookie("access_token", serviceResponse.accessToken).apply {
            path = "/"
            isHttpOnly = true
            maxAge = 60 * 60 // 1시간
        }

        val refreshToken = Cookie("refresh_token", serviceResponse.refreshToken).apply {
            path = "/"
            isHttpOnly = true
            maxAge = 60 * 60 * 24 * 30 // 30일
        }

        val expiredAt = Cookie("expired_at", serviceResponse.expiredAt.toString()).apply {
            path = "/"
            isHttpOnly = true
            maxAge = 60 * 60 // 1시간
        }

        val authority = Cookie("authority", serviceResponse.authority.toString()).apply {
            path = "/"
            isHttpOnly = true
            maxAge = 60 * 60 // 1시간
        }

        response.addCookie(accessToken)
        response.addCookie(refreshToken)
        response.addCookie(expiredAt)
        response.addCookie(authority)

        return ResponseEntity.ok().build()
    }

    @PatchMapping
    fun reissueToken(@RequestHeader("RefreshToken") refreshToken: String): ResponseEntity<TokenResponse> =
        reissueTokenService.execute(refreshToken)
            .let { ResponseEntity.ok(it) }

    @DeleteMapping
    fun logout(@RequestHeader("RefreshToken") refreshToken: String): ResponseEntity<Unit> =
        logoutService.execute(refreshToken)
            .let { ResponseEntity.noContent().build() }
}