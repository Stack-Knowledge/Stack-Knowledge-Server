package com.stack.knowledge.domain.image.application.spi

interface QueryImagePort {
    fun queryImageUrl(fileName: String): String
}