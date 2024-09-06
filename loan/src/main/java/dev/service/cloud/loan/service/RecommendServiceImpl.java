package dev.service.cloud.loan.service;

import dev.service.cloud.loan.repository.LoanProductRepository;
import dev.service.cloud.loan.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendServiceImpl implements RecommendService{
    private final LoanProductRepository loanProductRepository;


    public String recommendLoan() {
        loanProductRepository.findAll();
        return "recommendLoan";
    }

}
