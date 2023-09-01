package com.stack.knowledge.domain.mission.presentation

import com.stack.knowledge.domain.mission.application.usecase.CreateMissionUseCase
import com.stack.knowledge.domain.mission.application.usecase.QueryAllMissionUseCase
import com.stack.knowledge.domain.mission.application.usecase.QueryMissionDetailsUseCase
import com.stack.knowledge.domain.mission.presentation.data.request.CreateMissionRequest
import com.stack.knowledge.domain.mission.presentation.data.response.MissionDetailsResponse
import com.stack.knowledge.domain.mission.presentation.data.response.MissionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/mission")
class MissionWebAdapter(
    private val queryAllMissionUseCase: QueryAllMissionUseCase,
    private val createMissionUseCase: CreateMissionUseCase,
    private val queryMissionDetailsUseCase: QueryMissionDetailsUseCase
) {
    @GetMapping
    fun queryAllMission(): ResponseEntity<List<MissionResponse>> =
        queryAllMissionUseCase.execute()
            .let { ResponseEntity.ok(it) }

    @GetMapping("/{mission_id}")
    fun queryMissionById(@PathVariable("mission_id") missionId: UUID): ResponseEntity<MissionDetailsResponse> =
        queryMissionDetailsUseCase.execute(missionId)
            .let { ResponseEntity.ok(it) }

    @PostMapping
    fun createMission(@RequestBody @Valid createMissionRequest: CreateMissionRequest): ResponseEntity<Void> =
        createMissionUseCase.execute(createMissionRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }
}