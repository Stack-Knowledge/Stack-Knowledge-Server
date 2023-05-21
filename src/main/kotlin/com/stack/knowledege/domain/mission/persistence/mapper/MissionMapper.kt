package com.stack.knowledege.domain.mission.persistence.mapper

import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.mission.persistence.entity.MissionEntity
import com.stack.knowledege.global.mapper.GenericMapper
import org.springframework.stereotype.Component

@Component
class MissionMapper : GenericMapper<Mission, MissionEntity> {

    override fun toDomain(entity: MissionEntity?): Mission? =
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

    override fun toEntity(domain: Mission): MissionEntity =
        domain.let {
            MissionEntity(
                id = it.id,
                title = it.title,
                content = it.content,
                duration = it.duration,
                timeLimit = it.timeLimit,
                isSolved = it.isSolved
            )
        }
}