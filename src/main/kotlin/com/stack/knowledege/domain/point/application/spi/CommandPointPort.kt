package com.stack.knowledege.domain.point.application.spi

import com.stack.knowledege.domain.point.domain.Point

interface CommandPointPort {
    fun save(point: Point)
}