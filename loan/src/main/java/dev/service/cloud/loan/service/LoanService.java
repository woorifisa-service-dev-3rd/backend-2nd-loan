package dev.service.cloud.loan.service;

import dev.service.cloud.loan.dto.response.LoanProductResponseDto;
import dev.service.cloud.loan.model.LoanProduct;
import dev.service.cloud.loan.model.Provider;

import java.util.List;

public interface LoanService {

    List<LoanProductResponseDto> loansearching();
    void test1();
}
