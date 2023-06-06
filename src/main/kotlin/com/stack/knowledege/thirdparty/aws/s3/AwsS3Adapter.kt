package com.stack.knowledege.thirdparty.aws.s3

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.stack.knowledege.domain.image.application.spi.UploadImagePort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import java.io.IOException

@Component
class AwsS3Adapter(
    private val amazonS3: AmazonS3,
    private val awsS3Properties: AwsS3Properties
) : UploadImagePort {

    override fun upload(multipartFile: MultipartFile) =
        inputS3(multipartFile, multipartFile.name)
            .run { getImageUrl(fileName = multipartFile.name) }

    private fun inputS3(multipartFile: MultipartFile, fileName: String) {
        val objectMetadata = ObjectMetadata()
        objectMetadata.contentLength = multipartFile.size
        objectMetadata.contentType = multipartFile.contentType
        try {
            amazonS3.putObject(
                PutObjectRequest(awsS3Properties.bucket, fileName, multipartFile.inputStream, objectMetadata)
                    .withCannedAcl(
                        CannedAccessControlList.PublicRead
                    )
            )
        } catch (e: IOException) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패했습니다.")
        }
    }

    override fun getImageUrl(fileName: String) {
        amazonS3.getUrl(awsS3Properties.bucket, fileName).toString()
    }
}