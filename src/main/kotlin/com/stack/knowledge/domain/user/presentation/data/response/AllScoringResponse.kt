package com.stack.knowledge.domain.user.presentation.data.response

data class AllScoringResponse(
    val response: List<QueryScoringResponse>
) {
    constructor(): this(arrayListOf())
}