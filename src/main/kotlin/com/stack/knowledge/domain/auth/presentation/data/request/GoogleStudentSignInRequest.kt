package com.stack.knowledge.domain.auth.presentation.data.request

import javax.validation.constraints.NotBlank

data class GoogleStudentSignInRequest(
    @NotBlank
    val code: String
)