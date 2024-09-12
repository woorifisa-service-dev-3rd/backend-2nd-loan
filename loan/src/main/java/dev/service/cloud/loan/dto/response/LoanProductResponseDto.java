package dev.service.cloud.loan.dto.response;

import dev.service.cloud.loan.model.LoanProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;

@Builder
@Getter @Setter
@ToString
public class LoanProductResponseDto {
    private BigDecimal interest_rate;
    private Long max_limit;
    private int required_credit_score;
    private TypeResponse type;
    private ProviderResponse provider;

    public static LoanProductResponseDto from(LoanProduct loanProduct){
        // Owner 엔터티 내 값들을 추출
        final BigDecimal interestRate= loanProduct.getInterestRate();
        final Long maxLimit = loanProduct.getMaxLimit();
        final int requiredCreditScore = loanProduct.getRequiredCreditScore();
        final TypeResponse type = TypeResponse.from(loanProduct.getLoanProductsType());
        final ProviderResponse provider = ProviderResponse.from(loanProduct.getProvider());
        // DTO 반환
        return new LoanProductResponseDto(interestRate,maxLimit,requiredCreditScore,type,provider);
    }
}
