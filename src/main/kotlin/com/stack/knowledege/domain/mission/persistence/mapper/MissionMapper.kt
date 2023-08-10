package com.stack.knowledege.domain.mission.persistence.mapper

import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.mission.persistence.entity.MissionJpaEntity
import org.springframework.stereotype.Component
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Component
interface MissionMapper {
    fun toDomain(entity: MissionJpaEntity?): Mission?
    fun toEntity(domain: Mission): MissionJpaEntity
}