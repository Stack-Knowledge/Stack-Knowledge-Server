package com.stack.knowledge.domain.user.presentation

import com.stack.knowledge.domain.user.application.usecase.QueryScoringPageDetailsUseCase
import com.stack.knowledge.domain.user.application.usecase.QueryScoringPageUseCase
import com.stack.knowledge.domain.user.application.usecase.ScoreSolveUseCase
import com.stack.knowledge.domain.user.presentation.data.request.ScoreSolveRequest
import com.stack.knowledge.domain.user.presentation.data.response.AllScoringResponse
import com.stack.knowledge.domain.user.presentation.data.response.ScoringDetailsResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserWebAdapter(
    private val queryScoringPageUseCase: QueryScoringPageUseCase,
    private val queryScoringPageDetailsUseCase: QueryScoringPageDetailsUseCase,
    private val scoreSolveUseCase: ScoreSolveUseCase
) {
    @GetMapping("/scoring/{page}")
    fun queryAllSolve(@PathVariable page: Int): ResponseEntity<List<AllScoringResponse>> =
        queryScoringPageUseCase.execute(page)
            .let { ResponseEntity.ok(it) }

    @GetMapping("/scoring/{solve_id}")
    fun querySolveDetails(@PathVariable("solve_id") solveId: UUID): ResponseEntity<ScoringDetailsResponse> =
        queryScoringPageDetailsUseCase.execute(solveId)
            .let { ResponseEntity.ok(it) }

    @PostMapping("/scoring/{solve_id}")
    fun scoreSolve(@PathVariable("solve_id") solveId: UUID, @RequestBody @Valid scoreSolveRequest: ScoreSolveRequest): ResponseEntity<Void> =
        scoreSolveUseCase.execute(solveId, scoreSolveRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }
}