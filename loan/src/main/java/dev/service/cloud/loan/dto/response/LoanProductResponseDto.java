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

        //pets
//        final List<Provider> providers = owner.getPets().stream().map(PetResponse :: from).collect(Collectors.toList());

//        final Provider provider = loanProduct.getProvider()
        final TypeResponse type = TypeResponse.from(loanProduct.getLoanProductsType());
        final ProviderResponse provider = ProviderResponse.from(loanProduct.getProvider());
//        final ProviderResponse providerResponse = ProviderResponse.builder().id(provider.getId()).name(provider.getName()).build();
//        final LoanProductsType type = loanProduct.getLoanProductsType();
//        final TypeResponse typeResponse = TypeResponse.builder().id(type.getId()).name(type.getName()).build();


        return new LoanProductResponseDto(interestRate,maxLimit,type,provider);
        // 추출된 값들을 기반으로 OwnerResponse 객체생성
//        return new OwnerResponse(id,firstName,lastName,address,city,telephone,pets);
    }
}
