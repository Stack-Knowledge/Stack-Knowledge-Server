package com.stack.knowledege.domain.point.persistence

import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.mission.persistence.mapper.MissionMapper
import com.stack.knowledege.domain.point.application.spi.PointPort
import com.stack.knowledege.domain.point.domain.Point
import com.stack.knowledege.domain.point.persistence.mapper.PointMapper
import com.stack.knowledege.domain.point.persistence.repository.PointJpaRepository
import org.springframework.stereotype.Component

@Component
class PointPersistenceAdapter(
    private val pointJpaRepository: PointJpaRepository,
    private val pointMapper: PointMapper,
    private val missionMapper: MissionMapper
) : PointPort {
    override fun save(point: Point) {
        pointJpaRepository.save(pointMapper.toEntity(point))
    }

    override fun queryPointByMission(mission: Mission): Point? =
        pointMapper.toDomain(pointJpaRepository.findByMission(missionMapper.toEntity(mission)))

    override fun queryPointTopByIdDesc(): Point? =
        pointMapper.toDomain(pointJpaRepository.findTopByOrderByMissionPointDesc())
}