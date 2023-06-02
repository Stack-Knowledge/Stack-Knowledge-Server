package com.stack.knowledege.domain.solvation.application.spi

import com.stack.knowledege.domain.solvation.domain.Solvation

interface CommandSolvationPort {
    fun save(solvation: Solvation)
}