package dev.service.cloud.loan.service;

import dev.service.cloud.loan.dto.response.LoanProductResponseDto;
import dev.service.cloud.loan.model.LoanProduct;
import dev.service.cloud.loan.repository.LoanProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService{

    private final LoanProductRepository loanProductRepository;

    public List<LoanProductResponseDto> loansearching(){
        List<LoanProduct> loanProducts = loanProductRepository.findAll();
        List<LoanProductResponseDto> loanProductResponseDtos =loanProducts.stream().map((loanProduct) -> LoanProductResponseDto.from(loanProduct)).collect(Collectors.toList());
        return loanProductResponseDtos;
    }

    public void test1(){
        List<LoanProduct> loanProducts = loanProductRepository.findAll();
        List<BigDecimal> test= loanProducts.stream().map(loanProduct -> loanProduct.getInterestRate()).collect(Collectors.toList());
        System.out.println(test);
        return;
    }

}
