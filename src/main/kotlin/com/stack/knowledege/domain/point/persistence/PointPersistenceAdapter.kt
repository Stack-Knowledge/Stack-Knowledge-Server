package com.stack.knowledege.domain.point.persistence

import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.mission.persistence.mapper.MissionMapper
import com.stack.knowledege.domain.point.application.spi.PointPort
import com.stack.knowledege.domain.point.domain.Point
import com.stack.knowledege.domain.point.persistence.mapper.PointMapper
import com.stack.knowledege.domain.point.persistence.repository.PointJpaRepository
import com.stack.knowledege.domain.solve.domain.Solve
import com.stack.knowledege.domain.solve.persistence.mapper.SolveMapper
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class PointPersistenceAdapter(
    private val pointJpaRepository: PointJpaRepository,
    private val pointMapper: PointMapper,
    private val missionMapper: MissionMapper,
    private val solveMapper: SolveMapper
) : PointPort {
    override fun save(point: Point) {
        pointJpaRepository.save(pointMapper.toEntity(point))
    }

    override fun queryPointByMission(mission: Mission): Point? =
        pointMapper.toDomain(pointJpaRepository.findByMission(missionMapper.toEntity(mission)))

    override fun queryPointBySolve(solve: Solve): Point? =
        pointMapper.toDomain(pointJpaRepository.findBySolve(solveMapper.toEntity(solve)))

    override fun queryTopByMissionIdOrderByMissionPointDesc(missionId: UUID): Point? =
        pointMapper.toDomain(pointJpaRepository.findTopByMissionIdOrderByMissionPointDesc(missionId))
}