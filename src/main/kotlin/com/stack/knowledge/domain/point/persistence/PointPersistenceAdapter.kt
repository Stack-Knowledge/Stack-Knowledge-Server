package com.stack.knowledge.domain.point.persistence

import com.stack.knowledge.domain.point.application.spi.PointPort
import com.stack.knowledge.domain.point.domain.Point
import com.stack.knowledge.domain.point.persistence.mapper.PointMapper
import com.stack.knowledge.domain.point.persistence.repository.PointJpaRepository
import com.stack.knowledge.domain.solve.domain.Solve
import com.stack.knowledge.domain.solve.persistence.mapper.SolveMapper
import org.springframework.stereotype.Component
import java.util.*

@Component
class PointPersistenceAdapter(
    private val pointJpaRepository: PointJpaRepository,
    private val pointMapper: PointMapper,
    private val solveMapper: SolveMapper
) : PointPort {
    override fun save(point: Point) {
        pointJpaRepository.save(pointMapper.toEntity(point))
    }

    override fun deleteAllByMissionIds(missionIds: List<UUID>) {
        pointJpaRepository.deleteAllByMissionIds(missionIds)
    }

    override fun queryPointBySolve(solve: Solve): Point? =
        pointMapper.toDomain(pointJpaRepository.findBySolve(solveMapper.toEntity(solve)))

    override fun queryTopByMissionIdOrderByMissionPoint(missionId: UUID): Point? =
        pointMapper.toDomain(pointJpaRepository.findTopByMissionIdOrderByMissionPoint(missionId))
}