package com.stack.knowledege.domain.solvation.domain

import java.util.*

class Solvation(
    val id: UUID,
    val answer: String,
    val user: UUID,
    val mission: UUID
)