package com.stack.knowledge.domain.mission.presentation

import com.stack.knowledge.domain.mission.application.service.CreateMissionService
import com.stack.knowledge.domain.mission.application.service.QueryAllMissionService
import com.stack.knowledge.domain.mission.application.service.QueryMissionDetailsService
import com.stack.knowledge.domain.mission.presentation.data.request.CreateMissionRequest
import com.stack.knowledge.domain.mission.presentation.data.response.MissionDetailsResponse
import com.stack.knowledge.domain.mission.presentation.data.response.MissionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/mission")
class MissionWebAdapter(
    private val queryAllMissionService: QueryAllMissionService,
    private val createMissionService: CreateMissionService,
    private val queryMissionDetailsService: QueryMissionDetailsService
) {
    @GetMapping
    fun queryAllMission(): ResponseEntity<List<MissionResponse>> =
        queryAllMissionService.execute()
            .let { ResponseEntity.ok(it) }

    @GetMapping("/{mission_id}")
    fun queryMissionById(@PathVariable("mission_id") missionId: UUID): ResponseEntity<MissionDetailsResponse> =
        queryMissionDetailsService.execute(missionId)
            .let { ResponseEntity.ok(it) }

    @PostMapping
    fun createMission(@RequestBody @Valid createMissionRequest: CreateMissionRequest): ResponseEntity<Unit> =
        createMissionService.execute(createMissionRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }
}