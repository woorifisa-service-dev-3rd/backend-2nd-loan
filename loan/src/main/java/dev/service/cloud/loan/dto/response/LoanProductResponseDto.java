package dev.service.cloud.loan.dto.response;

import dev.service.cloud.loan.model.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@Getter
@ToString
public class LoanProductResponseDto {

    private LoanProductsType loanProductsType;  //대출유형
    private BigDecimal interestRate;    //이자율
    private Integer maxLimit;   //대출한도
    private Long repaymentPeriod;  //상환기간
    private LoanProductsFeature loanProductsFeature;    //상품특징Id
    private ApplicationMethod applicationMethod;    //대출신청방법
    private Integer requiredCreditScore;
    private Provider provider;


    public static LoanProductResponseDto from(LoanProduct loanProduct) {
        String featureName = loanProduct.getLoanProductsFeature().getName();
        System.out.println("featureName = " + featureName);

        return LoanProductResponseDto.builder()
                .loanProductsType(loanProduct.getLoanProductsType())
                .interestRate(loanProduct.getInterestRate())
                .maxLimit(loanProduct.getMaxLimit())
                .repaymentPeriod(loanProduct.getRepaymentPeriod())
                .applicationMethod(loanProduct.getApplicationMethod())
                .requiredCreditScore(loanProduct.getRequiredCreditScore())
                .provider(loanProduct.getProvider())
                .build();
    }
}
