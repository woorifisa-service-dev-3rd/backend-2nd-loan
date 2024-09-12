package dev.service.cloud.loan.service;

import dev.service.cloud.loan.dto.response.LoanProductResponseDto;
import dev.service.cloud.loan.exception.ErrorCode;
import dev.service.cloud.loan.exception.LoanException;
import dev.service.cloud.loan.model.LoanProduct;
import dev.service.cloud.loan.repository.LoanProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class LoanProductServiceImpl implements LoanProductService {
    private final LoanProductRepository loanProductRepository;

    @Override
    public LoanProductResponseDto findById(Long loandId) {
//        loanProductRepository.findById(loandId).get();
        loanProductRepository.findById(loandId).orElseThrow(() ->
                new LoanException(ErrorCode.LOAN_PRODUCT_NOT_FOUND, "ff")
        );
        LoanProduct tmp = loanProductRepository.findById(loandId).get();
        log.info("{}", tmp.toString());
        return LoanProductResponseDto.toDto(tmp);
    }
}
