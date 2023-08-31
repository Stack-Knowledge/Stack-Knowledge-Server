package com.stack.knowledge.domain.solve.persistence.repository

import com.stack.knowledge.domain.mission.domain.Mission
import com.stack.knowledge.domain.solve.domain.constant.SolveStatus
import com.stack.knowledge.domain.solve.persistence.entity.SolveJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface SolveJpaRepository : CrudRepository<SolveJpaEntity, UUID> {
    fun findByMission(mission: Mission): SolveJpaEntity?
    fun findAllBySolveStatus(solveStatus: SolveStatus): List<SolveJpaEntity>
}