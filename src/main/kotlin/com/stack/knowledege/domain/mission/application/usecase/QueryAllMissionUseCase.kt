package com.stack.knowledege.domain.mission.application.usecase

import com.stack.knowledege.domain.mission.application.spi.QueryMissionPort
import com.stack.knowledege.domain.mission.presentation.data.response.MissionResponse
import com.stack.knowledege.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryAllMissionUseCase(
    private val queryMissionPort: QueryMissionPort
) {
    fun execute(): List<MissionResponse> =
        queryMissionPort.queryAllMission()
            .map {
                MissionResponse(
                    id = it.id,
                    title = it.title,
                    introduce = it.introduce,
                    content = it.content,
                    timeLimit = it.timeLimit,
                    isSolved = it.isSolved
                )
            }
}