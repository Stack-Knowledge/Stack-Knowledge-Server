package com.stack.knowledge.domain.mission.application.usecase

import com.stack.knowledge.domain.mission.application.spi.MissionPort
import com.stack.knowledge.domain.mission.exception.MissionNotFoundException
import com.stack.knowledge.domain.mission.presentation.data.response.MissionDetailsResponse
import com.stack.knowledge.common.annotation.usecase.ReadOnlyUseCase
import com.stack.knowledge.domain.mission.domain.constant.MissionStatus
import com.stack.knowledge.domain.mission.exception.MissionNotOpenedException
import java.util.UUID

@ReadOnlyUseCase
class QueryMissionDetailsUseCase(
    private val missionPort: MissionPort
) {
    fun execute(id: UUID): MissionDetailsResponse {
        val mission = missionPort.queryMissionById(id) ?: throw MissionNotFoundException()

        if (mission.missionStatus != MissionStatus.OPENED)
            throw MissionNotOpenedException()

        return MissionDetailsResponse(
            title = mission.title,
            content = mission.content,
            timeLimit = mission.timeLimit
        )
    }
}