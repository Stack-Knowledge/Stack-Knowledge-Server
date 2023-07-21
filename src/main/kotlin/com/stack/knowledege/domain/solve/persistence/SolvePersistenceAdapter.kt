package com.stack.knowledege.domain.solve.persistence

import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.solve.application.spi.SolvePort
import com.stack.knowledege.domain.solve.domain.Solve
import com.stack.knowledege.domain.solve.persistence.mapper.SolveMapper
import com.stack.knowledege.domain.solve.persistence.repository.SolveRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class SolvePersistenceAdapter(
    private val solveRepository: SolveRepository,
    private val solveMapper: SolveMapper
) : SolvePort {
    override fun save(solve: Solve) {
        solveRepository.save(solveMapper.toEntity(solve))
    }

    override fun queryAllSolve(): List<Solve> =
        solveRepository.findAll().map { solveMapper.toDomain(it)!! }

    override fun querySolveById(solveId: UUID): Solve? =
        solveMapper.toDomain(solveRepository.findByIdOrNull(solveId))

    override fun querySolveByMission(mission: Mission): Solve? =
        solveMapper.toDomain(solveRepository.queryByMission(mission))
}