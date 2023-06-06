package com.stack.knowledege.thirdparty.aws

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationPropertiesScan("cloud.aws.s3")
@ConstructorBinding
class AwsProperties(
    val bucket: String
)