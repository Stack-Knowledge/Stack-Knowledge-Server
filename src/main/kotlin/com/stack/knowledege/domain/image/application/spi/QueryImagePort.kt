package com.stack.knowledege.domain.image.application.spi

interface QueryImagePort {
    fun getImageUrl(fileName: String): String
}