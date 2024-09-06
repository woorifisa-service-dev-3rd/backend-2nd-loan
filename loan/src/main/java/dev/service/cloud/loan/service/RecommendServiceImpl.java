package dev.service.cloud.loan.service;

import dev.service.cloud.loan.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendServiceImpl implements RecommendService{
    private final MemberRepository memberRepositroy;

    @Override
    public String recommendLoan(String memberId) {

        return "recommendLoan";
    }

}
