package com.stack.knowledge.global.error

enum class ErrorCode(
        val message: String,
        val status: Int
) {
    // auth
    EXPIRED_TOKEN("토큰 만료", 401),
    FORBIDDEN("Forbidden",403),
    INVALID_TOKEN("유효하지 않은 토큰", 401),
    UNAUTHORIZED("권한 없음", 401),
    INVALID_ROLE("검증 되지 않은 권합입니다.", 401),
    REFRESH_TOKEN_NOT_FOUND("존재하지 않는 Refresh-token 입니다.", 404),

    // item
    ITEM_NOT_FOUND("존재하지 않는 상품입니다.", 404),

    // order
    LACK_POINT("포인트가 부족합니다.", 400),
    LACK_ORDER("주문된 상품의 수보다 지급하려는 상품의 수가 많습니다.", 400),
    ORDER_NOT_FOUND("존재하지 않는 주문입니다.", 404),

    // mission
    MISSION_NOT_FOUND("존재하지 않는 미션입니다.", 404),
    MISSION_NOT_OPENED("미션은 12시 30분 ~ 7시 30분 사이에만 풀 수 있습니다.", 403),

    // image
    MISMATCH_IMAGE_EXTENSION("이미지 파일이 아닙니다.", 400),
    FILE_SIZE_TOO_SMALL("파일 사이즈가 너무 작습니다.", 400),

    // solve
    SOLVE_NOT_FOUND("존재하지 않는 풀이입니다.", 404),
    ALREADY_SCORED("이미 채점을 맨 문제입니다.", 409),
    ALREADY_SOLVED("이미 푼 문제입니다.", 409),
    UNSUPPORTED_SOLVE_STATUS("지원하지 않는 문제 채점 방식입니다.", 400),

    // user
    USER_NOT_FOUND("존재하지 않는 유저입니다.", 404),

    // student
    STUDENT_NOT_FOUND("존재하지 않는 학생입니다.", 404),

    // point
    POINT_NOT_FOUND("존재하지 않는 미션 포인트입니다.", 404),

    // server
    INTERNAL_SERVER_ERROR("서버 내부 에러", 500)
}