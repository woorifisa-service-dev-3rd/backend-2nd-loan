package dev.service.cloud.loan.dto.response;

import dev.service.cloud.loan.model.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Builder
@Getter
@ToString
public class LoanProductResponseDto {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private int interestRate;
    private int MaxLimit;
    private LocalDate repaymentPeriod;
    private int requiredCreditScore;
    private LoanProductsType loanProductsType;
    private Provider provider;
    private LoanProductsFeature loanProductsFeatures;
    private ApplicationMethod applicationMethod;
    private MemeberLoanProduct memeberLoanProducts;


    public static LoanProductResponseDto fromEntity(LoanProduct loanProduct) {
        return LoanProductResponseDto.builder()
                .id(loanProduct.getId())
                .startDate(loanProduct.getStartDate())
                .endDate(loanProduct.getEndDate())
                .interestRate(loanProduct.getInterestRate())
                .MaxLimit(loanProduct.getMaxLimit())
                .repaymentPeriod(loanProduct.getRepaymentPeriod())
                .requiredCreditScore(loanProduct.getRequiredCreditScore())
                .loanProductsType(loanProduct.getLoanProductsType())
                .provider(loanProduct.getProvider())
                .loanProductsFeatures(loanProduct.getLoanProductsFeatures())
                .applicationMethod(loanProduct.getApplicationMethod()).build();
    }
}
