package dev.service.cloud.loan.service;


import dev.service.cloud.loan.dto.response.LoanProductResponseDto;
import dev.service.cloud.loan.model.LoanProduct;
import dev.service.cloud.loan.repository.LoanProductRepository;
import dev.service.cloud.loan.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendServiceImpl implements RecommendService {
    private final MemberRepository memberRepositroy;

    private final LoanProductRepository loanProductRepository;


    @Override
    public List<LoanProductResponseDto> recommendByPoint(int point) throws Exception {
        List<LoanProduct> recommendedProducts = loanProductRepository.findEligibleLoanProducts(point);

        if (recommendedProducts.isEmpty()) {
            throw new Exception("recommendLoanByPoint 호출간 : 추천 로직 오류 - 추천 상품이 없습니다.");
        }

        List<LoanProductResponseDto> recommendedProductsDto
                = recommendedProducts.stream().map(LoanProductResponseDto::fromEntity).collect(Collectors.toList());

        log.info("서빙되는 리스트 확인 {}", recommendedProductsDto);
        return recommendedProductsDto;
    }

    @Override
    public List<LoanProductResponseDto> findAll() {
        return null;
    }
}
