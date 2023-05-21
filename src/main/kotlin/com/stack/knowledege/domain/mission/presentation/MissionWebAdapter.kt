package com.stack.knowledege.domain.mission.presentation

import com.stack.knowledege.domain.mission.application.usecase.QueryAllMissionUseCase
import com.stack.knowledege.domain.mission.presentation.data.response.MissionResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mission")
class MissionWebAdapter(
    private val queryAllMissionUseCase: QueryAllMissionUseCase
) {
    @GetMapping
    fun queryAllMission(): ResponseEntity<List<MissionResponse>> =
        queryAllMissionUseCase.execute()
            .let { ResponseEntity.ok(it) }
}