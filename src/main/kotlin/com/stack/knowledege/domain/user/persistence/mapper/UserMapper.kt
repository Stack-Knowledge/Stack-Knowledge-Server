package com.stack.knowledege.domain.user.persistence.mapper

import com.stack.knowledege.domain.user.domain.User
import com.stack.knowledege.domain.user.persistence.entity.UserEntity
import com.stack.knowledege.global.mapper.GenericMapper
import org.springframework.stereotype.Component

@Component
class UserMapper : GenericMapper<User, UserEntity> {
    override fun toDomain(entity: UserEntity?): User? =
        entity?.let {
            User(
                id = it.id,
                email = it.email,
                name = it.name,
                grade = it.grade,
                number = it.number,
                point = it.point,
                roles = it.roles
            )
        }

    override fun toEntity(domain: User): UserEntity =
        domain?.let {
            UserEntity(
                id = it.id,
                email = it.email,
                name = it.name,
                grade = it.grade,
                number = it.number,
                point = it.point,
                roles = it.roles
            )
        }
}