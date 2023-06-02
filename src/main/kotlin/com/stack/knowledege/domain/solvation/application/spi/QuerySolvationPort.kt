package com.stack.knowledege.domain.solvation.application.spi

import com.stack.knowledege.domain.solvation.domain.Solvation
import java.util.UUID

interface QuerySolvationPort {
    fun queryAllSolvation(): List<Solvation>
    fun querySolvationById(solvationId: UUID): Solvation?
}