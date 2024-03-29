package com.stack.knowledge.domain.auth.presentation.data.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.stack.knowledge.domain.user.domain.constant.Authority
import java.time.ZonedDateTime

data class TokenResponse (
    val accessToken: String,
    val refreshToken: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH-mm-ss")
    val expiredAt: ZonedDateTime,
    val authority: Authority
)