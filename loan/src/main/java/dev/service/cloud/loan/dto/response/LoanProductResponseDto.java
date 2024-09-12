package dev.service.cloud.loan.dto.response;

import dev.service.cloud.loan.model.LoanProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.math.BigDecimal;

@Builder
@Getter
@ToString
public class LoanProductResponseDto {
    private BigDecimal interest_rate;
    private Integer max_limit;
    private Integer required_credit_score;
    private TypeResponseDto type;
    private ProviderResponseDto provider;

    public static LoanProductResponseDto toDto(LoanProduct loanProduct){
        // Owner 엔터티 내 값들을 추출
        final BigDecimal interestRate= loanProduct.getInterestRate();
        final Integer maxLimit = loanProduct.getMaxLimit();
        final Integer requiredCreditScore = loanProduct.getRequiredCreditScore();
        final TypeResponseDto type = TypeResponseDto.toDto(loanProduct.getLoanProductsType());
        final ProviderResponseDto provider = ProviderResponseDto.toDto(loanProduct.getProvider());
        // DTO 반환
        return new LoanProductResponseDto(interestRate,maxLimit,requiredCreditScore,type,provider);
    }
}
