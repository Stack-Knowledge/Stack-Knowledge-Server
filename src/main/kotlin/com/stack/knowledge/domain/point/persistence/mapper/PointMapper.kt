package com.stack.knowledge.domain.point.persistence.mapper

import com.stack.knowledge.domain.mission.exception.MissionNotFoundException
import com.stack.knowledge.domain.mission.persistence.repository.MissionJpaRepository
import com.stack.knowledge.domain.point.domain.Point
import com.stack.knowledge.domain.point.persistence.entity.PointJpaEntity
import com.stack.knowledge.global.mapper.GenericMapper
import com.stack.knowledge.domain.solve.exception.SolveNotFoundException
import com.stack.knowledge.domain.solve.persistence.repository.SolveJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class PointMapper(
    private val missionJpaRepository: MissionJpaRepository,
    private val solveJpaRepository: SolveJpaRepository
) : GenericMapper<Point, PointJpaEntity> {
    override fun toDomain(entity: PointJpaEntity?): Point? =
        entity?.let {
            Point(
                id = entity.id,
                missionPoint = entity.missionPoint,
                mission = entity.mission.id,
                solve = entity.solve.id
            )
    }

    override fun toEntity(domain: Point): PointJpaEntity {
        val mission = missionJpaRepository.findByIdOrNull(domain.mission) ?: throw MissionNotFoundException()
        val solve = solveJpaRepository.findByIdOrNull(domain.solve) ?: throw SolveNotFoundException()

        return PointJpaEntity(
            id = domain.id,
            missionPoint = domain.missionPoint,
            mission = mission,
            solve = solve
        )
    }
}