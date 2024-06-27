package com.stack.knowledge.global.security.oauth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User

class UserInfo(
    authorities: Collection<GrantedAuthority?>,
    attributes: Map<String, Any>,
    nameAttributeKey: String?
) : OAuth2User {
    override fun getAttributes(): Map<String, Any> = attributes

    override fun getAuthorities(): Collection<GrantedAuthority?> = authorities

    override fun getName(): String? = null
}