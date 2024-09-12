package dev.service.cloud.loan.service;

import dev.service.cloud.loan.dto.response.LoanProductResponseDto;
import dev.service.cloud.loan.model.LoanProduct;
import dev.service.cloud.loan.repository.LoanProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoanProductServiceImpl implements LoanProductService{
    private final LoanProductRepository loanProductRepository;


    @Override
    public List<LoanProductResponseDto> searchLoans(String sort, String data) {
        List<LoanProductResponseDto> loanProductResponseDtos;
        if(sort.equals("asc")){
            Sort order = Sort.by(data).ascending();
            List<LoanProduct> loanProducts = loanProductRepository.findAll(order);
            loanProductResponseDtos =loanProducts.stream().map((loanProduct) -> LoanProductResponseDto.toDto(loanProduct)).collect(Collectors.toList());
        } else{
            Sort order = Sort.by(data).descending();
            List<LoanProduct> loanProducts = loanProductRepository.findAll(order);
            loanProductResponseDtos =loanProducts.stream().map((loanProduct) -> LoanProductResponseDto.toDto(loanProduct)).collect(Collectors.toList());
        }

        return loanProductResponseDtos;
    }

}
