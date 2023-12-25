package com.stack.knowledge.thirdparty.feign.error

import com.stack.knowledge.global.error.exception.InternalServerError
import com.stack.knowledge.global.security.exception.ExpiredTokenException
import com.stack.knowledge.global.security.exception.ForbiddenException
import com.stack.knowledge.thirdparty.feign.exception.UnAuthorizedException
import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder

class FeignErrorDecoder : ErrorDecoder {
    override fun decode(methodKey: String?, response: Response): Exception {
        val status = response.status()

        if (status >= 400) {
            when (status) {
                401 -> throw UnAuthorizedException()
                403 -> throw ForbiddenException()
                419 -> throw ExpiredTokenException()
                else -> throw InternalServerError()
            }
        }

        return FeignException.errorStatus(methodKey, response)
    }
}