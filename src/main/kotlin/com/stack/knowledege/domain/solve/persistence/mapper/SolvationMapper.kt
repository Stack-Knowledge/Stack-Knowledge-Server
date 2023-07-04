package com.stack.knowledege.domain.solve.persistence.mapper

import com.stack.knowledege.domain.mission.exception.MissionNotFoundException
import com.stack.knowledege.domain.mission.persistence.repository.MissionRepository
import com.stack.knowledege.domain.solve.domain.Solve
import com.stack.knowledege.domain.solve.persistence.entity.SolveJpaEntity
import com.stack.knowledege.domain.student.persistence.repository.StudentJpaRepository
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.global.mapper.GenericMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class SolvationMapper(
    private val missionRepository: MissionRepository,
    private val studentJpaRepository: StudentJpaRepository
) : GenericMapper<Solve, SolveJpaEntity> {
    override fun toDomain(entity: SolveJpaEntity?): Solve? =
        entity?.let {
            Solve(
                id = it.id,
                solvation = it.solvation,
                isSolved = it.isSolved,
                solveStatus = it.solveStatus,
                student = it.student.id,
                mission = it.mission.id
            )
        }


    override fun toEntity(domain: Solve): SolveJpaEntity {
        val mission = missionRepository.findByIdOrNull(domain.mission) ?: throw MissionNotFoundException()
        val student = studentJpaRepository.findByIdOrNull(domain.student) ?: throw UserNotFoundException()
        return domain.let {
            SolveJpaEntity(
                id = it.id,
                solvation = it.solvation,
                isSolved = it.isSolved,
                solveStatus = it.solveStatus,
                student = student,
                mission = mission
            )
        }
    }
}