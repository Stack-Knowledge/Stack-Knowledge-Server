package com.stack.knowledge.thirdparty.notification.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("fcm")
class FcmProperties(
    val fileUrl: String
)