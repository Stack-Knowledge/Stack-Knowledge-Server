package com.stack.knowledege.domain.solve.persistence

import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.solve.application.spi.SolvePort
import com.stack.knowledege.domain.solve.domain.Solve
import com.stack.knowledege.domain.solve.persistence.mapper.SolveMapper
import com.stack.knowledege.domain.solve.persistence.repository.SolveJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class SolvePersistenceAdapter(
    private val solveJpaRepository: SolveJpaRepository,
    private val solveMapper: SolveMapper
) : SolvePort {
    override fun save(solve: Solve) {
        solveJpaRepository.save(solveMapper.toEntity(solve))
    }

    override fun queryAllSolve(): List<Solve> =
        solveJpaRepository.findAll().map { solveMapper.toDomain(it)!! }

    override fun querySolveById(solveId: UUID): Solve? =
        solveMapper.toDomain(solveJpaRepository.findByIdOrNull(solveId))

    override fun querySolveByMission(mission: Mission): Solve? =
        solveMapper.toDomain(solveJpaRepository.queryByMission(mission))
}