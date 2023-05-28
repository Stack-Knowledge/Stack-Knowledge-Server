package com.stack.knowledege.domain.auth.presentation.data.request

import javax.validation.constraints.NotBlank

class GAuthSignInRequest(
    @field:NotBlank
    val code: String
)