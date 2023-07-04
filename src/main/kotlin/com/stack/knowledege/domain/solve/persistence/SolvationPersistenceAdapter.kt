package com.stack.knowledege.domain.solve.persistence

import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.solve.application.spi.SolvationPort
import com.stack.knowledege.domain.solve.domain.Solve
import com.stack.knowledege.domain.solve.persistence.mapper.SolvationMapper
import com.stack.knowledege.domain.solve.persistence.repository.SolvationRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class SolvationPersistenceAdapter(
    private val solvationRepository: SolvationRepository,
    private val solvationMapper: SolvationMapper
) : SolvationPort {
    override fun save(solve: Solve) {
        solvationRepository.save(solvationMapper.toEntity(solve))
    }

    override fun queryAllSolvation(): List<Solve> =
        solvationRepository.findAll().map { solvationMapper.toDomain(it)!! }

    override fun querySolvationById(solvationId: UUID): Solve? =
        solvationMapper.toDomain(solvationRepository.findByIdOrNull(solvationId))

    override fun querySolvationByMission(mission: Mission): Solve? =
        solvationMapper.toDomain(solvationRepository.queryByMission(mission))
}