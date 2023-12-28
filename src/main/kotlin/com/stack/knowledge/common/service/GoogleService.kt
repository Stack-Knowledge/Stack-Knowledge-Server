package com.stack.knowledge.common.service

import com.stack.knowledge.thirdparty.feign.client.GoogleAuthClient
import com.stack.knowledge.thirdparty.feign.client.GoogleInfoClient
import com.stack.knowledge.thirdparty.feign.dto.request.GoogleCodeRequest
import com.stack.knowledge.thirdparty.google.GoogleProperties
import org.springframework.stereotype.Component
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Component
class GoogleService(
    private val googleProperties: GoogleProperties,
    private val googleAuthClient: GoogleAuthClient,
    private val googleInfoClient: GoogleInfoClient
) {
    fun queryGoogleEmailAndName(code: String): Pair<String, String> {
        val googleCodeRequest = GoogleCodeRequest(
            code = URLDecoder.decode(code, StandardCharsets.UTF_8),
            clientId = googleProperties.clientId,
            clientSecret = googleProperties.clientSecret,
            redirectUri = googleProperties.redirectUrl
        )

        val response = googleAuthClient.googleAuth(googleCodeRequest)

        val googleInfoResponse = googleInfoClient.googleInfo(response.access_token)

        return Pair(googleInfoResponse.email, googleInfoResponse.name)
    }
}