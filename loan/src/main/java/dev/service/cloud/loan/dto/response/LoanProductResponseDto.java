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

    private String loanProductsType;  //대출유형
    private BigDecimal interestRate;    //이자율
    private Integer maxLimit;   //대출한도
    private Long repaymentPeriod;  //상환기간
    private String loanProductsFeature;    //상품특징Id에 대한 이름
    private String applicationMethod;    //대출신청방법 id에 대한 대출신청방법
    private Integer requiredCreditScore;
    private String provider;


    public static LoanProductResponseDto from(LoanProduct loanProduct) {
        String featureName = loanProduct.getLoanProductsFeature().getName();
        LoanProductsFeature loanProductsFeature = loanProduct.getLoanProductsFeature();
        ApplicationMethod applicationMethod = loanProduct.getApplicationMethod();
        LoanProductsType loanProductsType = loanProduct.getLoanProductsType();
        Provider provider = loanProduct.getProvider();

        System.out.println("featureName = " + featureName);
        System.out.println("loanProductsFeature = " + loanProductsFeature);

        return LoanProductResponseDto.builder()
                .loanProductsType(loanProductsType.getName())
                .interestRate(loanProduct.getInterestRate())
                .loanProductsFeature(loanProductsFeature.getName())
                .maxLimit(loanProduct.getMaxLimit())
                .repaymentPeriod(loanProduct.getRepaymentPeriod())
                .applicationMethod(String.valueOf(applicationMethod.getLoanProducts()))
                .requiredCreditScore(loanProduct.getRequiredCreditScore())
                .provider(provider.getName())
                .build();
    }
}
