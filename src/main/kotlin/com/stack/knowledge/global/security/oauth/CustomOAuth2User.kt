package com.stack.knowledge.global.security.oauth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.DefaultOAuth2User

class CustomOAuth2User(
    authorities: Collection<GrantedAuthority>,
    attributes: Map<String, Any>,
    nameAttributeKey: String,
    val email: String
) : DefaultOAuth2User(authorities, attributes, nameAttributeKey) {
    companion object {
        fun of(userNameAttributeName: String, attributes: Map<String, Any>, authorities: Collection<GrantedAuthority>,): CustomOAuth2User =
            CustomOAuth2User(
                attributes = attributes,
                nameAttributeKey = userNameAttributeName,
                authorities = authorities,
                email = attributes["email"] as String
            )
    }
}