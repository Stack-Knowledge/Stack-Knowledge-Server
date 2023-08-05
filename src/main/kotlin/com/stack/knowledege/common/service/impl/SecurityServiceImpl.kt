package com.stack.knowledege.common.service.impl

import com.stack.knowledege.common.service.SecurityService
import com.stack.knowledege.common.spi.SecurityPort
import org.springframework.stereotype.Service
import java.util.*

@Service
class SecurityServiceImpl(
    private val securityPort: SecurityPort
) : SecurityService {
    override fun queryCurrentUserId(): UUID =
        securityPort.queryCurrentUserId()

    override fun queryCurrentUserAuthority(): String =
        securityPort.queryCurrentUserAuthority()
}