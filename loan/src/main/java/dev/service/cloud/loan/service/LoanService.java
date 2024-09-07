package dev.service.cloud.loan.service;

import dev.service.cloud.loan.dto.request.LoanRequestDto;
import dev.service.cloud.loan.dto.response.LoanResponseDto;
import dev.service.cloud.loan.model.MemberLoanProduct;

import java.util.List;

public interface LoanService {
    List<LoanResponseDto> findAllLoans();
    LoanResponseDto addNewLoan(LoanRequestDto loanRequestDto);
    void checkLoanCondition(int memberCreditScore, int requiredCreditScore, int loanMaxLimit, long loanAmount);

    List<LoanResponseDto> getLoanListByMemberId(Long memberId);
}
