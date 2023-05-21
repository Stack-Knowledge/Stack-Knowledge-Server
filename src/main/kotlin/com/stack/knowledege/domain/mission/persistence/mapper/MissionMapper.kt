package com.stack.knowledege.domain.mission.persistence.mapper

import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.global.mapper.GenericMapper
import org.springframework.stereotype.Component

@Component
class MissionMapper : GenericMapper<Mission, Mission> {

    override fun toDomain(entity: Mission?): Mission? =
        entity?.let {
            Mission(
                id = it.id,
                title = it.title,
                content = it.content,
                duration = it.duration,
                timeLimit = it.timeLimit,
                isSolved = it.isSolved
            )
        }

    override fun toEntity(domain: Mission): Mission =
        domain.let {
            Mission(
                id = it.id,
                title = it.title,
                content = it.content,
                duration = it.duration,
                timeLimit = it.timeLimit,
                isSolved = it.isSolved
            )
        }
}