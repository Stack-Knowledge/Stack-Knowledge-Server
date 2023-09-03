package com.stack.knowledge.thirdparty.gauth

import com.stack.knowledge.domain.auth.application.spi.GAuthPort
import com.stack.knowledge.thirdparty.gauth.properties.GAuthProperties
import gauth.GAuth
import gauth.GAuthToken
import gauth.GAuthUserInfo
import org.springframework.stereotype.Component

@Component
class GAuthAdapter(
    private val gauth: GAuth,
    private val gauthProperties: GAuthProperties
) : GAuthPort {
    override fun queryGAuthToken(code: String): GAuthToken =
        gauth.generateToken(
            code,
            gauthProperties.clientId,
            gauthProperties.clientSecret,
            gauthProperties.redirectUri
        )


    override fun queryUserInfo(accessToken: String): GAuthUserInfo =
        gauth.getUserInfo(accessToken)
}