package dev.service.cloud.loan.dto.response;

import dev.service.cloud.loan.model.LoanProduct;
import dev.service.cloud.loan.model.LoanProductsType;
import dev.service.cloud.loan.model.Member;
import dev.service.cloud.loan.model.MemberLoanProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@ToString
public class LoanResponseDto {
    private String memberName;
    private String loanProductTypeName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long loanAmount;
    private LocalDate loanDueDate;
    private Integer repaymentCount;
    private Integer latePaymentCount;
    private Long memberId;
    private Long loanProductId;
    private Long goalAmount;
    private Long totalPaidAmount;
    private Long totalRepaymentAmount;

    public static LoanResponseDto toDto(MemberLoanProduct memberLoanProduct) {
        Member member = memberLoanProduct.getMember();
        LoanProduct loanProduct = memberLoanProduct.getLoanProduct();
        LoanProductsType loanProductsType = loanProduct.getLoanProductsType();

        return LoanResponseDto.builder()
                .memberName(member.getName())
                .loanProductTypeName(loanProductsType.getName())
                .startDate(memberLoanProduct.getStartDate())
                .endDate(memberLoanProduct.getEndDate())
                .loanAmount(memberLoanProduct.getLoanAmount())
                .loanDueDate(memberLoanProduct.getLoanDueDate())
                .repaymentCount(memberLoanProduct.getRepaymentCount())
                .latePaymentCount(memberLoanProduct.getLatePaymentCount())
                .goalAmount(memberLoanProduct.getGoalAmount())
                .totalPaidAmount(memberLoanProduct.getTotalPaidAmount())
                .totalRepaymentAmount(memberLoanProduct.getTotalRepaymentAmount())
                .memberId(member.getId())
                .loanProductId(loanProduct.getId())
                .build();
    }

    public static List<LoanResponseDto> toDtos(List<MemberLoanProduct> memberLoanProducts) {
        return memberLoanProducts.stream()
                .map(memberLoanProduct -> toDto(memberLoanProduct))
                .collect(Collectors.toList());
    }

}
