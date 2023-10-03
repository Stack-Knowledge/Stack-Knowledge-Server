package com.stack.knowledge.domain.student.presentation

import com.stack.knowledge.domain.image.application.usecase.UploadImageUseCase
import com.stack.knowledge.domain.student.application.usecase.QueryAllStudentsRankingUseCase
import com.stack.knowledge.domain.student.application.usecase.QueryStudentPointInfoUseCase
import com.stack.knowledge.domain.student.presentation.data.response.AllStudentsRankingResponse
import com.stack.knowledge.domain.student.presentation.data.response.StudentPointResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/student")
class StudentWebAdapter(
    private val queryAllStudentsRankingUseCase: QueryAllStudentsRankingUseCase,
    private val queryStudentPointInfoUseCase: QueryStudentPointInfoUseCase,
    private val uploadImageUseCase: UploadImageUseCase
) {
    @GetMapping("/ranking")
    fun queryAllStudentsRanking(): ResponseEntity<List<AllStudentsRankingResponse>> =
        queryAllStudentsRankingUseCase.execute()
            .let { ResponseEntity.ok(it) }

    @GetMapping("/my")
    fun queryStudentPointInfo(): ResponseEntity<StudentPointResponse> =
        queryStudentPointInfoUseCase.execute()
            .let { ResponseEntity.ok(it) }

    @PostMapping("/image")
    fun uploadImage(@RequestPart("image") image: MultipartFile): ResponseEntity<String> =
        uploadImageUseCase.execute(image)
            .let { ResponseEntity.ok(it) }
}