package com.stack.knowledge.domain.user.persistence.mapper

import com.stack.knowledge.domain.user.domain.User
import com.stack.knowledge.domain.user.persistence.entity.UserJpaEntity
import com.stack.knowledge.global.mapper.GenericMapper
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
                authority = it.authority,
                approveStatus = it.approveStatus
            )
        }

    override fun toEntity(domain: User): UserJpaEntity =
        domain.let {
            UserJpaEntity(
                id = it.id,
                email = it.email,
                name = it.name,
                profileImage = it.profileImage,
                authority = it.authority,
                approveStatus = it.approveStatus
            )
        }
}