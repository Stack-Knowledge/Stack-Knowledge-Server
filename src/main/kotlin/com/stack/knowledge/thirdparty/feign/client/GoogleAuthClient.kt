package com.stack.knowledge.thirdparty.feign.client

import com.stack.knowledge.thirdparty.feign.dto.request.GoogleCodeRequest
import com.stack.knowledge.thirdparty.feign.dto.response.GoogleTokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(value = "googleAuth", url = "https://oauth2.googleapis.com/token")
interface GoogleAuthClient {
    @PostMapping
    fun googleAuth(googleCodeRequest: GoogleCodeRequest): GoogleTokenResponse
}