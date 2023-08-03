package com.stack.knowledege.global.security

import com.stack.knowledege.global.security.spi.SecurityPort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class SecurityAdapter: SecurityPort {
    override fun queryCurrentUserId(): UUID {
        println(UUID.fromString(SecurityContextHolder.getContext().authentication.name))
        return UUID.fromString(SecurityContextHolder.getContext().authentication.name)
    }
}