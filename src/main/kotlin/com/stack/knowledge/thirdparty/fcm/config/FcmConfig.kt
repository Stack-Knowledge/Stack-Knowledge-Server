package com.stack.knowledge.thirdparty.fcm.config

import com.stack.knowledge.thirdparty.fcm.property.FcmProperties
import org.springframework.context.annotation.Configuration
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import javax.annotation.PostConstruct

@Configuration
class FcmConfig(
    private val fcmProperties: FcmProperties
) {
    @PostConstruct
    private fun initialize() {
        runCatching {
            FirebaseA
            URL(fcmProperties.fileUrl).openStream().use {
                Files.copy(it, Paths.get(Path))
            }
        }
    }
}