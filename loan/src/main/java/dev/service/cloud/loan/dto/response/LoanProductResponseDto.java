package dev.service.cloud.loan.dto.response;

import dev.service.cloud.loan.model.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@ToString
public class LoanProductResponseDto {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal interestRate;
    private Integer maxLimit;
    private Long repaymentPeriod;
    private Integer requiredCreditScore;
    private String loanProductsTypeName;  // loanProductsType의 이름만 반환
    private String provider;
    private String loanProductsFeature;
    private String applicationMethod;

    // fromEntity() 메소드에서 memberLoanProducts 제외
    public static LoanProductResponseDto toDTO(LoanProduct loanProduct) {
        return LoanProductResponseDto.builder()
                .id(loanProduct.getId())
                .startDate(loanProduct.getStartDate())
                .endDate(loanProduct.getEndDate())
                .interestRate(loanProduct.getInterestRate())
                .maxLimit(loanProduct.getMaxLimit())
                .repaymentPeriod(loanProduct.getRepaymentPeriod())
                .requiredCreditScore(loanProduct.getRequiredCreditScore())
                .loanProductsTypeName(loanProduct.getLoanProductsType().getName())
                .provider(loanProduct.getProvider().getName())
                .loanProductsFeature(loanProduct.getLoanProductsFeature().getName())
                .applicationMethod(loanProduct.getApplicationMethod().getName())
                .build();
    }
}