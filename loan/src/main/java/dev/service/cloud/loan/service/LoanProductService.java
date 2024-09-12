package dev.service.cloud.loan.service;

import dev.service.cloud.loan.dto.response.LoanProductResponseDto;

import java.util.List;

public interface LoanProductService {
    // 대출상품 리스트조회
    List<LoanProductResponseDto> searchLoans(String sort, String data);

}
