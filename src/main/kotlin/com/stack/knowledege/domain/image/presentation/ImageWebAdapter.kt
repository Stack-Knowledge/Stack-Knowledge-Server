package com.stack.knowledege.domain.image.presentation

import com.stack.knowledege.domain.image.application.usecase.UploadImageUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/image")
class ImageWebAdapter(
    private val updateImageUseCase: UploadImageUseCase,
    private val uploadImageUseCase: UploadImageUseCase
) {
    @PostMapping
    fun uploadImage(@RequestPart multipartFile: MultipartFile): ResponseEntity<String> =
        updateImageUseCase.execute(multipartFile)
            .let { ResponseEntity.ok(it) }

    @PatchMapping
    fun updateImage(multipartFile: MultipartFile): ResponseEntity<String> =
        uploadImageUseCase.execute(multipartFile)
            .let { ResponseEntity.ok(it) }
}