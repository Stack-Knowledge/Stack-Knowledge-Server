package com.stack.knowledege.domain.student.presentation

import com.stack.knowledege.domain.student.application.usecase.queryAllStudentsRankUseCase
import com.stack.knowledege.domain.student.presentation.data.response.AllStudentsRankResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/student")
class StudentWebAdapter(
    private val queryAllStudentsRankUseCase: queryAllStudentsRankUseCase
) {
    @GetMapping("/rank")
    fun execute(): ResponseEntity<List<AllStudentsRankResponse>> =
        queryAllStudentsRankUseCase.execute()
            .let { ResponseEntity.ok(it) }
}