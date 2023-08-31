package com.stack.knowledge.domain.solve.persistence.mapper

import com.stack.knowledge.domain.mission.exception.MissionNotFoundException
import com.stack.knowledge.domain.mission.persistence.repository.MissionJpaRepository
import com.stack.knowledge.domain.solve.domain.Solve
import com.stack.knowledge.domain.solve.persistence.entity.SolveJpaEntity
import com.stack.knowledge.domain.student.persistence.repository.StudentJpaRepository
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import com.stack.knowledge.global.mapper.GenericMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class SolveMapper(
    private val missionJpaRepository: MissionJpaRepository,
    private val studentJpaRepository: StudentJpaRepository
) : GenericMapper<Solve, SolveJpaEntity> {
    override fun toDomain(entity: SolveJpaEntity?): Solve? =
        entity?.let {
            Solve(
                id = it.id,
                solvation = it.solvation,
                solveStatus = it.solveStatus,
                student = it.student.id,
                mission = it.mission.id
            )
        }

    override fun toEntity(domain: Solve): SolveJpaEntity {
        val mission = missionJpaRepository.findByIdOrNull(domain.mission) ?: throw MissionNotFoundException()
        val student = studentJpaRepository.findByIdOrNull(domain.student) ?: throw UserNotFoundException()

        return SolveJpaEntity(
            id = domain.id,
            solvation = domain.solvation,
            solveStatus = domain.solveStatus,
            student = student,
            mission = mission
        )
    }
}