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
    FAILED_SEND_EMAIL("이메일 발송에 실패했습니다.", 400),
    NOT_VERIFIED_EMAIL("인증되지 않은 이메일입니다.", 401),
    NOT_VERIFIED_CODE("인증되지 않은 인증키입니다.", 401),
    ALREADY_EXIST_EMAIL("이미 존재하는 이메일입니다.", 409),
    MISMATCH_USER_PASSWORD("일치하지 않는 비밀번호입니다.", 400),
    INVALID_ROLE("검증 되지 않은 권합입니다.", 401),

    // item
    ITEM_NOT_FOUND("존재하지 않는 상품입니다.", 404),

    // image
    MISMATCH_IMAGE_EXTENSION("이미지 파일이 아닙니다.", 400),
    IMAGE_ALREADY_EXIST("이미 프로필 사진이 존재합니다.", 400)
}