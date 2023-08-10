package com.stack.knowledege.domain.user.persistence.mapper

import com.stack.knowledege.domain.user.domain.User
import com.stack.knowledege.domain.user.persistence.entity.UserJpaEntity
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy
import org.springframework.stereotype.Component

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Component
interface UserMapper {
    fun toDomain(entity: UserJpaEntity?): User?
    fun toEntity(domain: User): UserJpaEntity
}