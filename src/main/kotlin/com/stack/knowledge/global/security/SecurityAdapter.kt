package com.stack.knowledge.global.security

import com.stack.knowledge.common.spi.SecurityPort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class SecurityAdapter : SecurityPort {
    override fun queryCurrentUserId(): UUID =
        UUID.fromString(SecurityContextHolder.getContext().authentication.name)

    override fun queryCurrentUserAuthority(): String =
        SecurityContextHolder.getContext().authentication.authorities.first().authority
}