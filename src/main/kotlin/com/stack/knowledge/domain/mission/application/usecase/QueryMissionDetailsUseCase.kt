package com.stack.knowledge.domain.mission.application.usecase

import com.stack.knowledge.domain.mission.application.spi.MissionPort
import com.stack.knowledge.domain.mission.exception.MissionNotFoundException
import com.stack.knowledge.domain.mission.presentation.data.response.MissionDetailsResponse
import com.stack.knowledge.common.annotation.usecase.ReadOnlyUseCase
import com.stack.knowledge.common.annotation.usecase.UseCase
import com.stack.knowledge.domain.mission.application.spi.CommandMissionPort
import com.stack.knowledge.domain.mission.application.spi.QueryMissionPort
import com.stack.knowledge.domain.mission.domain.constant.MissionStatus
import com.stack.knowledge.domain.mission.exception.MissionNotOpenedException
import com.stack.knowledge.domain.time.application.spi.CommandTimePort
import com.stack.knowledge.domain.time.domain.Time
import java.time.LocalDateTime
import java.util.UUID

@UseCase
class QueryMissionDetailsUseCase(
    private val queryMissionPort: QueryMissionPort,
    private val commandTimePort: CommandTimePort
) {
    fun execute(id: UUID): MissionDetailsResponse {
        val mission = queryMissionPort.queryMissionById(id) ?: throw MissionNotFoundException()

        if (mission.missionStatus != MissionStatus.OPENED)
            throw MissionNotOpenedException()

        val time = Time(
            id = UUID.randomUUID(),
            mission = mission.id,
            createdAt = LocalDateTime.now()
        )

        commandTimePort.save(time)

        return MissionDetailsResponse(
            title = mission.title,
            content = mission.content,
            timeLimit = mission.timeLimit
        )
    }
}