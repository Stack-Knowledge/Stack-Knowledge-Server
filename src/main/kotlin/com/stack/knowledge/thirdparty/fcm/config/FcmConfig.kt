package com.stack.knowledge.thirdparty.fcm.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.stack.knowledge.thirdparty.fcm.property.FcmProperties
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
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
            val options: FirebaseOptions
            options.toBuilder()
                .setCredentials(
                    GoogleCredentials
                        .fromStream(ClassPathResource(fcmProperties.fileUrl).inputStream)
                        .createScoped(listOf("fireBase Scope"))
                ).build()

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options)
            }
        }.onFailure {
            it.printStackTrace()
        }
    }
}