package com.stack.knowledege.domain.item.persistence.mapper

import com.stack.knowledege.domain.item.domain.Item
import com.stack.knowledege.domain.item.persistence.entity.ItemJpaEntity
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
interface ItemMapper {
    fun toDomain(entity: ItemJpaEntity?): Item?
    fun toEntity(domain: Item): ItemJpaEntity
}