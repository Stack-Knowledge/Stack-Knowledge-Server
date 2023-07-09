package com.stack.knowledege.domain.solve.application.spi

import com.stack.knowledege.domain.solve.domain.Solve

interface CommandSolvaePort {
    fun save(solve: Solve)
}