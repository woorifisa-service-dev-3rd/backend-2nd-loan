package dev.service.cloud.loan.service;

import dev.service.cloud.loan.model.LoanProduct;
import dev.service.cloud.loan.repository.LoanProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoanProductServiceImpl implements LoanProductService {
    LoanProductRepository loanProductRepository;

    @Override
    public List<LoanProduct> findAllLoanProducts() {
        loanProductRepository.findAll();
        return List.of();
    }
}
