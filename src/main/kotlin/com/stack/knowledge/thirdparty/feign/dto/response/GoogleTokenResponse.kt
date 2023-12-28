package com.stack.knowledge.thirdparty.feign.dto.response

data class GoogleTokenResponse(
    val access_token: String,
    val expires_in: String,
    val scope: String,
    val token_type: String,
    val id_token: String
)