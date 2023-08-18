package com.stack.knowledege.domain.point.persistence

import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.point.application.spi.PointPort
import com.stack.knowledege.domain.point.domain.Point
import com.stack.knowledege.domain.point.persistence.mapper.PointMapper
import com.stack.knowledege.domain.point.persistence.repository.PointJpaRepository
import org.springframework.stereotype.Component

@Component
class PointPersistenceAdapter(
    private val pointJpaRepository: PointJpaRepository,
    private val pointMapper: PointMapper
) : PointPort {
    override fun queryPointByMission(mission: Mission): Point? =
        pointMapper.toDomain(pointJpaRepository.findByMission(mission))
}