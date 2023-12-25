package com.stack.knowledge.domain.auth.application.service

import com.stack.knowledge.common.annotation.usecase.UseCase
import com.stack.knowledge.thirdparty.feign.client.GoogleAuthClient
import com.stack.knowledge.thirdparty.feign.client.GoogleInfoClient

@UseCase
class GoogleSignInService(
    private val googleAuthClient: GoogleAuthClient,
    private val googleInfoClient: GoogleInfoClient
) {
    fun execute() {
//        googleAuthClient.googleAuth()
    }
}