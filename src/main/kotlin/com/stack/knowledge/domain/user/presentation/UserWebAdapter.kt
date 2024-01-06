package com.stack.knowledge.domain.user.presentation

import com.stack.knowledge.domain.user.application.service.*
import com.stack.knowledge.domain.user.presentation.data.request.ScoreSolveRequest
import com.stack.knowledge.domain.user.presentation.data.request.UpdateUserApproveStatusRequest
import com.stack.knowledge.domain.user.presentation.data.response.AllScoringResponse
import com.stack.knowledge.domain.user.presentation.data.response.AllSignUpRequestTeacherResponse
import com.stack.knowledge.domain.user.presentation.data.response.ScoringDetailsResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserWebAdapter(
    private val queryScoringPageService: QueryScoringPageService,
    private val queryScoringPageDetailsService: QueryScoringPageDetailsService,
    private val scoreSolveUseCase: ScoreSolveService,
    private val updateUserApproveStatusService: UpdateUserApproveStatusService,
    private val queryAllSignUpRequestedTeacherService: QueryAllSignUpRequestedTeacherService
) {
    @GetMapping("/scoring")
    fun queryAllSolve(): ResponseEntity<List<AllScoringResponse>> =
        queryScoringPageService.execute()
            .let { ResponseEntity.ok(it) }

    @GetMapping("/scoring/{solve_id}")
    fun querySolveDetails(@PathVariable("solve_id") solveId: UUID): ResponseEntity<ScoringDetailsResponse> =
        queryScoringPageDetailsService.execute(solveId)
            .let { ResponseEntity.ok(it) }

    @GetMapping("/teacher")
    fun queryAllSignUpRequestedTeacher(): ResponseEntity<List<AllSignUpRequestTeacherResponse>> =
        queryAllSignUpRequestedTeacherService.execute()
            .let { ResponseEntity.ok(it) }

    @PostMapping("/scoring/{solve_id}")
    fun scoreSolve(@PathVariable("solve_id") solveId: UUID, @RequestBody @Valid scoreSolveRequest: ScoreSolveRequest): ResponseEntity<Unit> =
        scoreSolveUseCase.execute(solveId, scoreSolveRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PatchMapping("/{user_id}")
    fun updateUserApproveStatus(@PathVariable("user_id") userId: UUID, @RequestBody updateUserApproveStatusRequest: UpdateUserApproveStatusRequest): ResponseEntity<Unit> =
        updateUserApproveStatusService.execute(userId, updateUserApproveStatusRequest)
            .let { ResponseEntity.noContent().build() }
}