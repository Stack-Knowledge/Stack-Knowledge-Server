package com.stack.knowledge.domain.point.persistence.repository

import com.stack.knowledge.domain.point.persistence.entity.PointJpaEntity
import com.stack.knowledge.domain.solve.persistence.entity.SolveJpaEntity
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface PointJpaRepository : CrudRepository<PointJpaEntity, Long> {
    fun findBySolve(solve: SolveJpaEntity): PointJpaEntity?
    fun findTopByMissionIdOrderByMissionPointAsc(missionId: UUID): PointJpaEntity?
    @Modifying
    @Query("delete from PointJpaEntity p where p.mission in :missionIds")
    fun deleteAllByMissionIds(missionIds: List<UUID>)
}