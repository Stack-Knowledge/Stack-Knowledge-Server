package com.stack.knowledege.domain.order.persistence.mapper

import com.stack.knowledege.domain.order.domain.Order
import com.stack.knowledege.domain.order.persistence.entity.OrderJpaEntity
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
interface OrderMapper {
    fun toDomain(entity: OrderJpaEntity?): Order?
    fun toEntity(domain: Order): OrderJpaEntity
}