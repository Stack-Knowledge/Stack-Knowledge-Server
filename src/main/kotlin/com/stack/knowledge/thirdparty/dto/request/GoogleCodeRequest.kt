package com.stack.knowledge.thirdparty.dto.request

class GoogleCodeRequest(
    val code: String,
    val clientId: String,
    val clientSecret: String,
    val redirectUri: String,
    val grantType: String
)