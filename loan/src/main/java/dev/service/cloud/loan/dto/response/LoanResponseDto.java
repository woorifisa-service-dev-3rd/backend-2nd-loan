package dev.service.cloud.loan.dto.response;

import dev.service.cloud.loan.model.LoanProduct;
import dev.service.cloud.loan.model.LoanProductsType;
import dev.service.cloud.loan.model.Member;
import dev.service.cloud.loan.model.MemberLoanProduct;
import lombok.Builder;
import lombok.Getter;
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
    private Long loanAmount;
    private LocalDate loanDueDate;

    public static LoanResponseDto toDto(MemberLoanProduct memberLoanProduct) {
        Member member = memberLoanProduct.getMember();
        System.out.println("member = " + member);
        LoanProduct loanProduct = memberLoanProduct.getLoanProduct();
        System.out.println("loanProduct = " + loanProduct);
        LoanProductsType loanProductsType = loanProduct.getLoanProductsType();
        System.out.println("loanProductsType = " + loanProductsType);

        return LoanResponseDto.builder()
                .memberName(member.getName())
                .loanProductTypeName(loanProductsType.getName())
                .startDate(memberLoanProduct.getStartDate())
                .loanAmount(memberLoanProduct.getLoanAmount())
                .loanDueDate(memberLoanProduct.getLoanDueDate())
                .build();
    }

    public static List<LoanResponseDto> toDtos(List<MemberLoanProduct> memberLoanProducts) {
        return memberLoanProducts.stream()
                .map(memberLoanProduct -> toDto(memberLoanProduct))
                .collect(Collectors.toList());
    }

}
