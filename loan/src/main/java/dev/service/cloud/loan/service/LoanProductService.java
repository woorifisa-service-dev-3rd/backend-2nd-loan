package dev.service.cloud.loan.service;

import dev.service.cloud.loan.dto.response.LoanProductResponseDto;

import java.util.List;

public interface LoanProductService {
    List<LoanProductResponseDto> getLoanProductDetails(Long loanId);

    List<LoanProductResponseDto> findAll();
}
