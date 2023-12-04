package com.stack.knowledge.domain.solve.persistence.repository

import com.stack.knowledge.domain.mission.persistence.entity.MissionJpaEntity
import com.stack.knowledge.domain.solve.domain.constant.SolveStatus
import com.stack.knowledge.domain.solve.persistence.entity.SolveJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface SolveJpaRepository : CrudRepository<SolveJpaEntity, UUID> {
    fun findAllBySolveStatusAndMissionOrderByCreatedAtDesc(solveStatus: SolveStatus, mission: MissionJpaEntity): List<SolveJpaEntity>
    fun findAllByStudentId(studentId: UUID): List<SolveJpaEntity>
    fun existsByStudentIdAndMissionId(studentId: UUID, missionId: UUID): Boolean
}