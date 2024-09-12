package dev.service.cloud.loan.service;

import dev.service.cloud.loan.dto.request.LoanRequestDto;
import dev.service.cloud.loan.dto.response.LoanResponseDto;

import java.util.List;

import dev.service.cloud.loan.dto.response.LoanProductResponseDto;

public interface LoanService {
    List<LoanResponseDto> findAllLoans();
    LoanResponseDto addNewLoan(LoanRequestDto loanRequestDto);
    void checkLoanCondition(int memberCreditScore, int requiredCreditScore, int loanMaxLimit, long loanAmount);
}
