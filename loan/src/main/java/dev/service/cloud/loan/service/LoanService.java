package dev.service.cloud.loan.service;

import dev.service.cloud.loan.dto.response.LoanResponseDto;

import java.util.List;

public interface LoanService {
    List<LoanResponseDto> findAllLoans();
}
