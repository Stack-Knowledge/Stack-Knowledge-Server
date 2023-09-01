package com.stack.knowledge.domain.solve.persistence

import com.stack.knowledge.domain.mission.domain.Mission
import com.stack.knowledge.domain.solve.application.spi.SolvePort
import com.stack.knowledge.domain.solve.domain.Solve
import com.stack.knowledge.domain.solve.domain.constant.SolveStatus
import com.stack.knowledge.domain.solve.persistence.mapper.SolveMapper
import com.stack.knowledge.domain.solve.persistence.repository.SolveJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class SolvePersistenceAdapter(
    private val solveJpaRepository: SolveJpaRepository,
    private val solveMapper: SolveMapper
) : SolvePort {
    override fun save(solve: Solve): Solve? =
       solveMapper.toDomain(solveJpaRepository.save(solveMapper.toEntity(solve)))

    override fun queryAllSolveBySolveStatus(solveStatus: SolveStatus): List<Solve> =
        solveJpaRepository.findAllBySolveStatus(solveStatus).map { solveMapper.toDomain(it)!! }

    override fun querySolveById(solveId: UUID): Solve? =
        solveMapper.toDomain(solveJpaRepository.findByIdOrNull(solveId))

    override fun querySolveByMission(mission: Mission): Solve? =
        solveMapper.toDomain(solveJpaRepository.findByMission(mission))

    override fun querySolveByStudentId(studentId: UUID): List<Solve> =
        solveJpaRepository.findAllByStudentId(studentId).map { solveMapper.toDomain(it)!! }

}