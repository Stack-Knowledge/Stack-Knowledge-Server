package com.stack.knowledege.thirdparty.aws.s3

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationPropertiesScan("cloud.aws.s3")
@ConstructorBinding
class AwsS3Properties(
    val bucket: String
)