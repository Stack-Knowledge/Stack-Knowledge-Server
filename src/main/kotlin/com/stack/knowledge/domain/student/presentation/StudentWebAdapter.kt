package com.stack.knowledge.domain.student.presentation

import com.stack.knowledge.domain.image.application.usecase.UpdateImageUseCase
import com.stack.knowledge.domain.image.application.usecase.UploadImageUseCase
import com.stack.knowledge.domain.student.application.usecase.QueryAllStudentsRankingUseCase
import com.stack.knowledge.domain.student.presentation.data.response.AllStudentsRankingResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/student")
class StudentWebAdapter(
    private val queryAllStudentsRankingUseCase: QueryAllStudentsRankingUseCase,
    private val uploadImageUseCase: UploadImageUseCase,
    private val updateImageUseCase: UpdateImageUseCase
) {
    @GetMapping("/ranking")
    fun queryAllStudentsRanking(): ResponseEntity<List<AllStudentsRankingResponse>> =
        queryAllStudentsRankingUseCase.execute()
            .let { ResponseEntity.ok(it) }

    @PostMapping("/image")
    fun uploadImage(@RequestPart multipartFile: MultipartFile): ResponseEntity<String> =
        uploadImageUseCase.execute(multipartFile)
            .let { ResponseEntity.ok(it) }

    @PatchMapping("/image")
    fun updateImage(@RequestPart multipartFile: MultipartFile): ResponseEntity<String> =
        updateImageUseCase.execute(multipartFile)
            .let { ResponseEntity.ok(it) }
}