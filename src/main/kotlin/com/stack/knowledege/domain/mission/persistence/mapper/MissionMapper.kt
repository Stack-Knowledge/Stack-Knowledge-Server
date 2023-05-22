package com.stack.knowledege.domain.mission.persistence.mapper

import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.mission.persistence.entity.MissionEntity
import com.stack.knowledege.domain.user.persistence.repository.UserRepository
import com.stack.knowledege.global.mapper.GenericMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.domain.user.presentation.data.response.UserResponse

@Component
class MissionMapper(
    private val userRepository: UserRepository
) : GenericMapper<Mission, MissionEntity> {

    override fun toDomain(entity: MissionEntity?): Mission? =
        entity?.let {
            Mission(
                id = it.id,
                title = it.title,
                content = it.content,
                duration = it.duration,
                timeLimit = it.timeLimit,
                isSolved = it.isSolved,
                user = UserResponse(
                    id = it.user.id,
                    name = it.user.name,
                    grade = it.user.grade,
                    number = it.user.number
                )
            )
        }

    override fun toEntity(domain: Mission): MissionEntity {
        val user = userRepository.findByIdOrNull(domain.user.id) ?: throw UserNotFoundException()

        return domain.let {
            MissionEntity(
                id = it.id,
                title = it.title,
                content = it.content,
                duration = it.duration,
                timeLimit = it.timeLimit,
                isSolved = it.isSolved,
                user = user
            )
        }
    }
}