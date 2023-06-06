package com.stack.knowledege.thirdparty.aws

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationPropertiesScan("cloud.aws.credentials")
@ConstructorBinding
class AwsProperties(
    val accessKey: String,
    val secretKey: String
)