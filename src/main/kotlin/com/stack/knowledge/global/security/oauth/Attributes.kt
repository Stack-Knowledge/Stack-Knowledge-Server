package com.stack.knowledge.global.security.oauth

data class Attributes(
    val attributes: Map<String, Any>,
    val nameAttributeKey: String,
    val name: String,
    val email: String,
    val pictureURL: String
) {
    companion object {
        fun of(userNameAttributeName: String, attributes: Map<String, Any>): Attributes =
            Attributes(
                attributes = attributes,
                nameAttributeKey = userNameAttributeName,
                name = attributes["name"] as String,
                email = attributes["email"] as String,
                pictureURL = attributes["picture"] as String
            )
    }
}