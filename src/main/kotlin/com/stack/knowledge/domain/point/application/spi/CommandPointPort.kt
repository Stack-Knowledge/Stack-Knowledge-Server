package com.stack.knowledge.domain.point.application.spi

import com.stack.knowledge.domain.point.domain.Point

interface CommandPointPort {
    fun save(point: Point)
}