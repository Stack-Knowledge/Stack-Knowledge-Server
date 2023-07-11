package com.stack.knowledege.global.error

enum class ErrorCode(
        val message: String,
        val status: Int
) {
    USER_NOT_FOUND("존재하지 않는 유저입니다.", 404),
    EXPIRED_TOKEN("토큰 만료", 401),
    FORBIDDEN("Forbidden",401),
    INVALID_TOKEN("유효하지 않은 토큰", 401),
    UNAUTHORIZED("권한 없음", 401),
    INTERNAL_SERVER_ERROR("서버 내부 에러", 500),
    INVALID_ROLE("검증 되지 않은 권합입니다.", 401),

    // item
    ITEM_NOT_FOUND("존재하지 않는 상품입니다.", 404),

    // mission
    MISSION_NOT_FOUND("존재하지 않는 미션입니다.", 404),
    FORBIDDEN_MISSION_COMMAND("미션을 제어할 권한이 없습니다.", 403),
    ALREADY_SOLVED_MISSION("이미 푼 미션입니다.", 403),

    // image
    MISMATCH_IMAGE_EXTENSION("이미지 파일이 아닙니다.", 400),
    IMAGE_ALREADY_EXIST("이미 프로필 사진이 존재합니다.", 400),

    // solve
    SOLVE_NOT_FOUND("존재하지 않는 풀이입니다.", 404),
    ONLY_STUDENT("문제는 학생만이 풀 수 있습니다.", 403),
    ALREADY_SCORED("이미 채점을 맨 문제입니다.", 403)
}