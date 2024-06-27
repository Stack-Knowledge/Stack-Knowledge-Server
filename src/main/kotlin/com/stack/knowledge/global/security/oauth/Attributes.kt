package com.stack.knowledge.global.security.oauth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.DefaultOAuth2User

data class CustomOAuth2User(
    val attributes: Map<String, Any>,
    val nameAttributeKey: String,
    val authorities: Collection<GrantedAuthority>,
    val name: String,
    val email: String,
    val pictureURL: String
) : DefaultOAuth2User(authorities, attributes, nameAttributeKey) {
    companion object {
        fun of(userNameAttributeName: String, attributes: Map<String, Any>, authorities: Collection<GrantedAuthority>,): CustomOAuth2User =
            CustomOAuth2User(
                attributes = attributes,
                nameAttributeKey = userNameAttributeName,
                authorities = authorities,
                name = attributes["name"] as String,
                email = attributes["email"] as String,
                pictureURL = attributes["picture"] as String
            )
    }
}