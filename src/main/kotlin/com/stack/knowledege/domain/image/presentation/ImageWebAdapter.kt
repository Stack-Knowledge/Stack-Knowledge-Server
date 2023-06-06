package com.stack.knowledege.domain.image.presentation

import com.stack.knowledege.domain.image.application.usecase.UploadImageUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/image")
class ImageWebAdapter(
    private val updateImageUseCase: UploadImageUseCase
) {
    @PostMapping
    fun execute(multipartFile: MultipartFile): ResponseEntity<Void> {
        updateImageUseCase.execute(multipartFile)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}