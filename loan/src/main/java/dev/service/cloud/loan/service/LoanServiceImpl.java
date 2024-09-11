package dev.service.cloud.loan.service;

import dev.service.cloud.loan.dto.response.LoanProductResponseDto;
import dev.service.cloud.loan.model.LoanProduct;
import dev.service.cloud.loan.repository.LoanProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    @Override
    public List<LoanProductResponseDto> loansearching_asc() {
        PageRequest pageRequest = PageRequest.of(0,6, Sort.by("maxLimit").ascending());
        Page<LoanProduct> loanProducts = loanProductRepository.findAll(pageRequest);
        List<LoanProductResponseDto> loanProductResponseDtos =loanProducts.stream().map((loanProduct) -> LoanProductResponseDto.from(loanProduct)).collect(Collectors.toList());
        return loanProductResponseDtos;
    }

}
