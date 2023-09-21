package com.stack.knowledge.thirdparty.fcm.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.stack.knowledge.thirdparty.fcm.property.FcmProperties
import org.springframework.context.annotation.Configuration
import java.io.File
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import javax.annotation.PostConstruct

@Configuration
class FcmConfig(
    private val fcmProperties: FcmProperties
) {
    companion object {
        const val PATH = "./credentials.json"
    }

    @PostConstruct
    fun initialize() {
        runCatching {
            URL(fcmProperties.fileUrl).openStream().use {
                Files.copy(it, Paths.get(PATH))
                val file = File(PATH)
                if (FirebaseApp.getApps().isEmpty()) {
                    val options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(file.inputStream()))
                        .build()
                    FirebaseApp.initializeApp(options)
                }
                file.delete()
            }
        }.onFailure {
            it.printStackTrace()
        }
    }
}