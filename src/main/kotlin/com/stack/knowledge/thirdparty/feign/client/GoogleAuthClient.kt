package com.stack.knowledge.thirdparty.feign.client

import com.stack.knowledge.thirdparty.dto.request.GoogleCodeRequest
import com.stack.knowledge.thirdparty.dto.response.GoogleTokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(url = "https://oauth2.googleapis.com/token")
interface GoogleAuthClient {

    @PostMapping
    fun googleAuth(googleCodeRequest: GoogleCodeRequest): GoogleTokenResponse

}