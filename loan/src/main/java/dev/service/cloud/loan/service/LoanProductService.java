package dev.service.cloud.loan.service;

import dev.service.cloud.loan.model.LoanProduct;

import java.util.List;

public interface LoanProductService {
    List<LoanProduct> findAllLoanProducts();
}
