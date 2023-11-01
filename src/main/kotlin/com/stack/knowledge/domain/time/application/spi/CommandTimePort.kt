package com.stack.knowledge.domain.time.application.spi

import com.stack.knowledge.domain.time.domain.Time

interface CommandTimePort {
    fun save(time: Time)
}