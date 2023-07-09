package com.stack.knowledege.domain.image.application.validator.impl

import com.stack.knowledege.domain.image.application.validator.ImageValidator
import com.stack.knowledege.domain.image.exception.MisMatchImageExtensionException
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class ImageValidatorImpl(

) : ImageValidator {
    override fun validateImageExtension(multipartFile: MultipartFile): String {
        val extension = multipartFile.originalFilename!!.substring(multipartFile.originalFilename!!.lastIndexOf(".")).lowercase()

        if (extension !in listOf(".jpg", ".jpeg", ".png")) {
            throw MisMatchImageExtensionException()
        }

        return extension
    }
}