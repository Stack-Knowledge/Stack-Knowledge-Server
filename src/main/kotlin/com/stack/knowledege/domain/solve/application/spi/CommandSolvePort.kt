package com.stack.knowledege.domain.solve.application.spi

import com.stack.knowledege.domain.solve.domain.Solve

interface CommandSolvePort {
    fun save(solve: Solve): Solve?
}