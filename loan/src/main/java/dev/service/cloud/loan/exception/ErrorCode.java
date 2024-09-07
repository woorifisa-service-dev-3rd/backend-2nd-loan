package dev.service.cloud.loan.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum ErrorCode {
    MEMBER_NOT_FOUND("해당하는 회원이 없습니다."),
    LOAN_PRODUCT_NOT_FOUND("해당하는 대출 상품이 없습니다."),
    LOAN_CONDITION_NOT_MATCH("대출 조건이 맞지 않습니다"),
    LOW_CREDIT_SCORE("신용 점수가 낮습니다."),
    OVER_MAX_LIMIT_LOAN_AMOUNT("최대 대출 한도를 넘었습니다.");

    private String message;
}
