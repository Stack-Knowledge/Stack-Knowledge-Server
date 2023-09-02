package com.stack.knowledge.domain.auth.presentation.data.request

import javax.validation.constraints.NotBlank

data class GAuthSignInRequest(
    @field:NotBlank
    val code: String
)