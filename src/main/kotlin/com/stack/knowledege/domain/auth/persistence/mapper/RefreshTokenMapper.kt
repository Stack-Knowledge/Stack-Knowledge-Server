package com.stack.knowledege.domain.auth.persistence.mapper

import com.stack.knowledege.domain.auth.domain.RefreshToken
import com.stack.knowledege.domain.auth.persistence.entity.RefreshTokenEntity
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
interface RefreshTokenMapper {

    fun toDomain(refreshTokenEntity: RefreshTokenEntity?): RefreshToken?
    fun toEntity(refreshToken: RefreshToken): RefreshTokenEntity

}