package com.stack.knowledge.global.security.oauth

import com.stack.knowledge.domain.user.application.spi.UserPort
import com.stack.knowledge.domain.user.domain.User
import com.stack.knowledge.domain.user.domain.constant.ApproveStatus
import com.stack.knowledge.domain.user.domain.constant.Authority
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class CustomOAuth2UserService(
    private val userPort: UserPort
) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        println("==================================")
        val delegate: OAuth2UserService<OAuth2UserRequest, OAuth2User> = DefaultOAuth2UserService()
        val oAuth2User = delegate.loadUser(userRequest)

        val nameAttributeName = userRequest!!.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName

        val authorities: Collection<GrantedAuthority> = ArrayList(oAuth2User.authorities)

        val customOAuth2User = CustomOAuth2User.of(
            nameAttributeName,
            oAuth2User.attributes,
            authorities
        )

        val user: User = createUser(customOAuth2User)

        return customOAuth2User
    }

    private fun createUser(attributes: CustomOAuth2User): User {
        val email = attributes.email

        return when (userPort.queryExistByEmail(email)) {
            true -> userPort.queryUserByEmail(email) ?: throw UserNotFoundException()
            false -> {
                val user = User(
                    id = UUID.randomUUID(),
                    email = email,
                    name = attributes.attributes["name"] as String,
                    profileImage = attributes.attributes["picture"] as String,
                    authority = Authority.ROLE_STUDENT,
                    approveStatus = ApproveStatus.APPROVED,
                    createdAt = LocalDateTime.now()
                )
                userPort.save(user)
            }
        }
    }
}