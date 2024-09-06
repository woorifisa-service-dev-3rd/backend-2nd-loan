package dev.service.cloud.loan.service;


import dev.service.cloud.loan.customexception.NoRecommendedProductsException;
import dev.service.cloud.loan.dto.response.LoanProductResponseDto;
import dev.service.cloud.loan.model.LoanProduct;
import dev.service.cloud.loan.model.Provider;
import dev.service.cloud.loan.repository.LoanProductRepository;
import dev.service.cloud.loan.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendServiceImpl implements RecommendService {


    private final LoanProductRepository loanProductRepository;


    @Override
    public List<LoanProductResponseDto> recommendByPoint(int point) throws Exception {
        List<LoanProduct> recommendedProducts = loanProductRepository.findEligibleLoanProducts(point);
        List<LoanProductResponseDto> recommendedProductsDto = new ArrayList<>();

        if (recommendedProducts.isEmpty()) {
            throw new NoRecommendedProductsException("추천 상품이 없습니다.");
        } else {
            recommendedProductsDto = recommendedProducts.stream()
                    .map(LoanProductResponseDto::fromEntity)
                    .collect(Collectors.toList());
        }
        log.info("서빙되는 리스트 확인 {}", recommendedProductsDto);
        return recommendedProductsDto;
    }

    @Override
    public List<LoanProductResponseDto> findAll() {
        return loanProductRepository.findAll().stream().map(LoanProductResponseDto::fromEntity).collect(Collectors.toList());

    }
}
