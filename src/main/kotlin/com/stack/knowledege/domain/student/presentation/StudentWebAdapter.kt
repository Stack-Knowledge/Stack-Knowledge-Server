package com.stack.knowledege.domain.student.presentation

import com.stack.knowledege.domain.student.application.usecase.QueryAllStudentsRankingUseCase
import com.stack.knowledege.domain.student.presentation.data.response.AllStudentsRankingResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/student")
class StudentWebAdapter(
    private val queryAllStudentsRankingUseCase: QueryAllStudentsRankingUseCase
) {
    @GetMapping("/ranking")
    fun execute(): ResponseEntity<List<AllStudentsRankingResponse>> =
        queryAllStudentsRankingUseCase.execute()
            .let { ResponseEntity.ok(it) }
}