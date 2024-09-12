package dev.service.cloud.loan.service;

import dev.service.cloud.loan.dto.response.LoanProductResponseDto;
import dev.service.cloud.loan.model.LoanProduct;
import dev.service.cloud.loan.model.Provider;

import java.util.List;

public interface LoanService {
    // 대출상품 리스트조회
    List<LoanProductResponseDto> loansearching();
    //오름차순정렬
    List<LoanProductResponseDto> loansearching_asc(String data);
    //내림차순정렬
    List<LoanProductResponseDto> loansearching_desc(String data);

}
