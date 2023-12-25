package com.stack.knowledge.thirdparty.feign.client

import com.stack.knowledge.thirdparty.feign.dto.response.GoogleInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(value = "googleInfo", url = "https://www.googleapis.com/oauth2/v1/userinfo")
interface GoogleInfoClient {
    @GetMapping("?alt=json&access_token={ACCESS_TOKEN}")
    fun googleInfo(@PathVariable("ACCESS_TOKEN") accessToken: String): GoogleInfoResponse
}