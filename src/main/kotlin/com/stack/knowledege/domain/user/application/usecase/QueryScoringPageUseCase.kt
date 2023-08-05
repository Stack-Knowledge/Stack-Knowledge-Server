package com.stack.knowledege.domain.user.application.usecase

import com.stack.knowledege.domain.solve.application.spi.QuerySolvePort
import com.stack.knowledege.domain.user.presentation.data.response.AllScoringResponse
import com.stack.knowledege.common.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryScoringPageUseCase(
    private val querySolvePort: QuerySolvePort
) {
    fun execute(): List<AllScoringResponse> {
        val solves = querySolvePort.queryAllSolve()

        return solves.map {
            AllScoringResponse(
                solveId = it.id,
                solveStatus = it.solveStatus,
                student = it.student
            )
        }
    }
}