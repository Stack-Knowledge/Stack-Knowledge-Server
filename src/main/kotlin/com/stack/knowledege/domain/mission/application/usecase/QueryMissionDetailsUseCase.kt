package com.stack.knowledege.domain.mission.application.usecase

import com.stack.knowledege.domain.mission.application.spi.MissionPort
import com.stack.knowledege.domain.mission.exception.MissionNotFoundException
import com.stack.knowledege.domain.mission.presentation.data.response.MissionDetailsResponse
import com.stack.knowledege.domain.common.annotation.usecase.ReadOnlyUseCase
import java.util.UUID

@ReadOnlyUseCase
class QueryMissionDetailsUseCase(
    private val missionPort: MissionPort
) {
    fun execute(id: UUID): MissionDetailsResponse {
        val mission = missionPort.queryMissionById(id) ?: throw MissionNotFoundException()

        return MissionDetailsResponse(
            title = mission.title,
            content = mission.content,
            timeLimit = mission.timeLimit
        )
    }
}