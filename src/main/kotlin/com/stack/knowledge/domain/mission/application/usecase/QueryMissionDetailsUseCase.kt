package com.stack.knowledge.domain.mission.application.usecase

import com.stack.knowledge.common.annotation.usecase.UseCase
import com.stack.knowledge.domain.mission.application.spi.QueryMissionPort
import com.stack.knowledge.domain.mission.domain.constant.MissionStatus
import com.stack.knowledge.domain.mission.exception.MissionNotFoundException
import com.stack.knowledge.domain.mission.exception.MissionNotOpenedException
import com.stack.knowledge.domain.mission.presentation.data.response.MissionDetailsResponse
import com.stack.knowledge.domain.time.application.spi.TimePort
import com.stack.knowledge.domain.time.domain.Time
import java.time.LocalDateTime
import java.util.*

@UseCase
class QueryMissionDetailsUseCase(
    private val queryMissionPort: QueryMissionPort,
    private val timePort: TimePort
) {
    fun execute(id: UUID): MissionDetailsResponse {
        val mission = queryMissionPort.queryMissionById(id) ?: throw MissionNotFoundException()

        val time = timePort.queryTimeByMission(mission)

        if (time == null) {
            timePort.save(
                Time(
                    id = UUID.randomUUID(),
                    mission = mission.id,
                    createdAt = LocalDateTime.now()
                )
            )
        }
        else {
            time.copy(createdAt = LocalDateTime.now())
        }

        if (mission.missionStatus != MissionStatus.OPENED)
            throw MissionNotOpenedException()

        return MissionDetailsResponse(
            title = mission.title,
            content = mission.content,
            timeLimit = mission.timeLimit
        )
    }
}