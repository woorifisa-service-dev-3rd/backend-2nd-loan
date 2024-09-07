package dev.service.cloud.loan.service;


import dev.service.cloud.loan.dto.response.LoanProductResponseDto;

import java.util.List;


public interface RecommendService {

    List<LoanProductResponseDto> recommendByPoint(int point) throws Exception;

    List<LoanProductResponseDto> findAll();

    List<LoanProductResponseDto> recommendLoanProductsforMember(Long memberId);
}
