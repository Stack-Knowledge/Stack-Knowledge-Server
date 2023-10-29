package com.stack.knowledge.domain.point.persistence.repository

import com.stack.knowledge.domain.point.persistence.entity.PointJpaEntity
import com.stack.knowledge.domain.solve.persistence.entity.SolveJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface PointJpaRepository : CrudRepository<PointJpaEntity, Long> {
    fun findBySolve(solveJpaEntity: SolveJpaEntity): PointJpaEntity?
    fun findTopByMissionIdOrderByMissionPointDesc(missionId: UUID): PointJpaEntity?
}