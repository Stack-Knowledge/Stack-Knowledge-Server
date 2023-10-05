package com.stack.knowledge.domain.solve.persistence.repository

import com.stack.knowledge.domain.mission.persistence.entity.MissionJpaEntity
import com.stack.knowledge.domain.solve.domain.constant.SolveStatus
import com.stack.knowledge.domain.solve.persistence.entity.SolveJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface SolveJpaRepository : CrudRepository<SolveJpaEntity, UUID> {
    fun findByMission(missionJpaEntity: MissionJpaEntity): SolveJpaEntity?
    fun findAllBySolveStatusAndMission(solveStatus: SolveStatus, missionJpaEntity: MissionJpaEntity): List<SolveJpaEntity>
    fun findAllByStudentId(studentId: UUID): List<SolveJpaEntity>
}