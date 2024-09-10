package dev.service.cloud.loan.dto.response;

import dev.service.cloud.loan.model.LoanProduct;
import dev.service.cloud.loan.model.LoanProductsType;
import dev.service.cloud.loan.model.Provider;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter @Setter
@ToString
public class LoanProductResponseDto {
    private BigDecimal interest_rate;
    private Long max_limit;

    private TypeResponse type;
    private ProviderResponse provider;

    public static LoanProductResponseDto from(LoanProduct loanProduct){
        // Owner 엔터티 내 값들을 추출
        final BigDecimal interestRate= loanProduct.getInterestRate();
        final Long maxLimit = loanProduct.getMaxLimit();
        final TypeResponse type = TypeResponse.from(loanProduct.getLoanProductsType());
        final ProviderResponse provider = ProviderResponse.from(loanProduct.getProvider());
        // DTO 반환
        return new LoanProductResponseDto(interestRate,maxLimit,type,provider);
    }
}
