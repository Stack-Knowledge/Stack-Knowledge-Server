package com.stack.knowledge.domain.user.application.usecase

import com.stack.knowledge.domain.solve.application.spi.QuerySolvePort
import com.stack.knowledge.domain.user.presentation.data.response.AllScoringResponse
import com.stack.knowledge.common.annotation.usecase.ReadOnlyUseCase
import com.stack.knowledge.domain.solve.domain.constant.SolveStatus

@ReadOnlyUseCase
class QueryScoringPageUseCase(
    private val querySolvePort: QuerySolvePort
) {
    fun execute(): List<AllScoringResponse> {
        val solves = querySolvePort.queryAllSolveBySolveStatus(SolveStatus.SCORING)

        return solves.map {
            AllScoringResponse(
                solveId = it.id,
                solveStatus = it.solveStatus,
                student = it.student
            )
        }
    }
}