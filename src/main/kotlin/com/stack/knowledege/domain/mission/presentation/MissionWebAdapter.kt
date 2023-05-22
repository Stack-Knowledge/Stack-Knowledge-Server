package com.stack.knowledege.domain.mission.presentation

import com.stack.knowledege.domain.mission.application.usecase.CreateMissionUseCase
import com.stack.knowledege.domain.mission.application.usecase.QueryAllMissionUseCase
import com.stack.knowledege.domain.mission.presentation.data.request.CreateMissionRequest
import com.stack.knowledege.domain.mission.presentation.data.response.MissionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mission")
class MissionWebAdapter(
    private val queryAllMissionUseCase: QueryAllMissionUseCase,
    private val createMissionUseCase: CreateMissionUseCase
) {
    @GetMapping
    fun queryAllMission(): ResponseEntity<List<MissionResponse>> =
        queryAllMissionUseCase.execute()
            .let { ResponseEntity.ok(it) }

    @PostMapping
    fun createMission(@RequestBody createMissionRequest: CreateMissionRequest): ResponseEntity<Void> =
        createMissionUseCase.execute(createMissionRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }
}