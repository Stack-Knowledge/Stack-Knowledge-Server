package com.stack.knowledege.domain.user.persistence.mapper

import com.stack.knowledege.domain.user.domain.User
import com.stack.knowledege.domain.user.persistence.entity.UserJpaEntity
import com.stack.knowledege.global.mapper.GenericMapper
import org.springframework.stereotype.Component

@Component
class UserMapper : GenericMapper<User, UserJpaEntity> {
    override fun toDomain(entity: UserJpaEntity?): User? =
        entity?.let {
            User(
                id = it.id,
                email = it.email,
                name = it.name,
                profileImage = it.profileImage!!,
                roles = it.roles
            )
        }

    override fun toEntity(domain: User): UserJpaEntity =
        domain?.let {
            UserJpaEntity(
                id = it.id,
                email = it.email,
                name = it.name,
                profileImage = it.profileImage,
                roles = it.roles
            )
        }
}