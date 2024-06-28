package com.stack.knowledge.global.security.oauth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import java.io.Serializable




//data class CustomOAuth2User(
//    attributes: Map<String, Any>,
//    nameAttributeKey: String,
//    authorities: Collection<GrantedAuthority>,
//    val name: String,
//    val email: String,
//    val pictureURL: String
//) : DefaultOAuth2User(authorities, attributes, nameAttributeKey) {
//    companion object {
//        fun of(userNameAttributeName: String, attributes: Map<String, Any>, authorities: Collection<GrantedAuthority>,): CustomOAuth2User =
//            CustomOAuth2User(
//                attributes = attributes,
//                nameAttributeKey = userNameAttributeName,
//                authorities = authorities,
//                name = attributes["name"] as String,
//                email = attributes["email"] as String,
//                pictureURL = attributes["picture"] as String
//            )
//    }
//}


//class CustomOAuth2User(
//    authorities: Collection<GrantedAuthority?>?,
//    attributes: Map<String?, Any?>?, nameAttributeKey: String?,
//    private val email: String, role: Role
//) : DefaultOAuth2User(authorities, attributes, nameAttributeKey) {
//    private val role: Role
//
//    /**
//     * Constructs a `DefaultOAuth2User` using the provided parameters.
//     *
//     * @param authorities      the authorities granted to the user
//     * @param attributes       the attributes about the user
//     * @param nameAttributeKey the key used to access the user's &quot;name&quot; from
//     * [.getAttributes]
//     */
//    init {
//        this.role = role
//    }
//}


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
//                name = attributes["name"] as String,
                email = attributes["email"] as String,
//                pictureURL = attributes["picture"] as String
            )
    }

    override fun getAttributes(): Map<String, Any> {
        return super.getAttributes()
    }

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return super.getAuthorities()
    }

    override fun getName(): String {
        return super.getName()
    }
}