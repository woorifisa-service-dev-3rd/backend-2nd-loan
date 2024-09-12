package dev.service.cloud.loan.service;

import dev.service.cloud.loan.dto.response.LoanProductResponseDto;

public interface LoanProductService {

    LoanProductResponseDto findById(Long loandId);
}
