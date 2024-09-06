package dev.service.cloud.loan.service;

import dev.service.cloud.loan.dto.response.LoanResponseDto;
import dev.service.cloud.loan.model.MemberLoanProduct;
import dev.service.cloud.loan.repository.MemberLoanProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService{
    private final MemberLoanProductRepository memberLoanProductRepository;

    public List<LoanResponseDto> findAllLoans() {
        List<MemberLoanProduct> all = memberLoanProductRepository.findAll();
        System.out.println("all = " + all);
        return LoanResponseDto.toDtos(all);
    }
}
