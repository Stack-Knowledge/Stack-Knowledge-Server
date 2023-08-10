package com.stack.knowledege.domain.auth.application.spi

import gauth.GAuthToken
import gauth.GAuthUserInfo

interface GAuthPort {
    fun queryGAuthToken(code: String): GAuthToken
    fun queryUserInfo(accessToken: String): GAuthUserInfo
}