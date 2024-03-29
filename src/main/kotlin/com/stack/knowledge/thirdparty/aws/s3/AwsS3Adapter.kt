package com.stack.knowledge.thirdparty.aws.s3

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.DeleteObjectRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.stack.knowledge.domain.image.application.spi.ImagePort
import com.stack.knowledge.thirdparty.aws.s3.config.properties.AwsS3Properties
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException

@Component
class AwsS3Adapter(
    private val amazonS3: AmazonS3,
    private val awsS3Properties: AwsS3Properties
) : ImagePort {

    override fun upload(multipartFile: MultipartFile, fileName: String): String =
        inputS3(multipartFile, fileName)
            .run { queryImageUrl(fileName = fileName) }

    override fun deleteImageUrl(fileName: String) {
        amazonS3.deleteObject(DeleteObjectRequest(awsS3Properties.bucket, fileName))
    }

    private fun inputS3(multipartFile: MultipartFile, fileName: String) {
        val objectMetadata = ObjectMetadata()

        objectMetadata.contentLength = multipartFile.size
        objectMetadata.contentType = multipartFile.contentType

        runCatching {
            amazonS3.putObject(
                PutObjectRequest(awsS3Properties.bucket, fileName, multipartFile.inputStream, objectMetadata)
                    .withCannedAcl(
                        CannedAccessControlList.PublicRead
                    )
            )
        }.onFailure {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패했습니다.")
        }
    }

    private fun queryImageUrl(fileName: String): String =
        amazonS3.getUrl(awsS3Properties.bucket, fileName).toString()
}