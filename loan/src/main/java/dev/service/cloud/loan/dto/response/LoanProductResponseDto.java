package dev.service.cloud.loan.dto.response;

import dev.service.cloud.loan.model.LoanProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
    private String type;


    /**
     * 대출상품 엔티티 -> 대출상품 DTO로 변환 메소드
     * @param loanProduct
     * @return LoanProductResponseDto
     */
    public static LoanProductResponseDto toDto(LoanProduct loanProduct) {
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
                .type(loanProduct.getLoanProductsType().getName())
                .build();
    }

    /**
     * 대출상품 엔티티 리스트 -> 대출상품 엔티티 DTO 변환 메소드
     * @param loanProducts
     * @return List<LoanProductResponseDto> 으로 변환
     */
    public static List<LoanProductResponseDto> toDtos(List<LoanProduct> loanProducts) {
        return loanProducts.stream().map(LoanProductResponseDto::toDto).collect(Collectors.toList());    // List.
    }
}
