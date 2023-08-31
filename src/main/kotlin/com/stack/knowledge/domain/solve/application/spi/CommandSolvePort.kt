package com.stack.knowledge.domain.solve.application.spi

import com.stack.knowledge.domain.solve.domain.Solve

interface CommandSolvePort {
    fun save(solve: Solve)
}