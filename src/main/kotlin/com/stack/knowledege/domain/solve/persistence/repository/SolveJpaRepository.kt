package com.stack.knowledege.domain.solve.persistence.repository

import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.solve.persistence.entity.SolveJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface SolveJpaRepository : CrudRepository<SolveJpaEntity, UUID> {
    fun queryByMission(mission: Mission): SolveJpaEntity?
}