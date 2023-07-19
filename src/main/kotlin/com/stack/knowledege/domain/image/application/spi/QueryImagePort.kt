package com.stack.knowledege.domain.image.application.spi

interface QueryImagePort {
    fun queryImageUrl(fileName: String): String
}