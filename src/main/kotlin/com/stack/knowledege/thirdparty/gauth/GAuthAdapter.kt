package com.stack.knowledege.thirdparty.gauth

import com.stack.knowledege.thirdparty.gauth.spi.GAuthPort
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