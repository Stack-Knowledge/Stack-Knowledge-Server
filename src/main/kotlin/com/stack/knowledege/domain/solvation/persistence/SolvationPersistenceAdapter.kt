package com.stack.knowledege.domain.solvation.persistence

import com.stack.knowledege.domain.solvation.application.spi.SolvationPort
import com.stack.knowledege.domain.solvation.domain.Solvation
import com.stack.knowledege.domain.solvation.persistence.mapper.SolvationMapper
import com.stack.knowledege.domain.solvation.persistence.repository.SolvationRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class SolvationPersistenceAdapter(
    private val solvationRepository: SolvationRepository,
    private val solvationMapper: SolvationMapper
) : SolvationPort {
    override fun save(solvation: Solvation) {
        solvationRepository.save(solvationMapper.toEntity(solvation))
    }

    override fun queryAllSolvation(): List<Solvation> =
        solvationRepository.findAll().map { solvationMapper.toDomain(it)!! }

    override fun querySolvationById(solvationId: UUID): Solvation? =
        solvationMapper.toDomain(solvationRepository.findByIdOrNull(solvationId))
}