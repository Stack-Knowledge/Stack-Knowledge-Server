package com.stack.knowledege.global.security

import com.stack.knowledege.common.spi.SecurityPort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class SecurityAdapter: SecurityPort {
    override fun queryCurrentUserId(): UUID =
        UUID.fromString(SecurityContextHolder.getContext().authentication.name)

    override fun queryCurrentUserAuthority(): String {
        println("==================================== query current")
        println(SecurityContextHolder.getContext().authentication.authorities.first().authority)
        println("==================================== query current")

        return SecurityContextHolder.getContext().authentication.authorities.first().authority

    }
}