package com.stack.knowledge.thirdparty.gauth

import com.stack.knowledge.domain.auth.application.spi.GAuthPort
import com.stack.knowledge.thirdparty.gauth.properties.GAuthProperties
import gauth.GAuth
import gauth.GAuthToken
import gauth.GAuthUserInfo
import org.springframework.stereotype.Component

@Component
class GAuthAdapter(
    private val gAuth: GAuth,
    private val gAuthProperties: GAuthProperties
) : GAuthPort {
    override fun queryGAuthToken(code: String): GAuthToken =
        gAuth.generateToken(
            code,
            gAuthProperties.clientId,
            gAuthProperties.clientSecret,
            gAuthProperties.redirectUri
        )


    override fun queryUserInfo(accessToken: String): GAuthUserInfo =
        gAuth.getUserInfo(accessToken)
}