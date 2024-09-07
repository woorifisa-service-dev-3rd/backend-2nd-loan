package dev.service.cloud.loan.service;


import dev.service.cloud.loan.customexception.NoRecommendedProductsException;
import dev.service.cloud.loan.dto.response.LoanProductResponseDto;
import dev.service.cloud.loan.model.LoanProduct;
import dev.service.cloud.loan.model.MemberLoanProduct;
import dev.service.cloud.loan.repository.LoanProductRepository;
import dev.service.cloud.loan.repository.MemberLoanProductRepository;
import dev.service.cloud.loan.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendServiceImpl implements RecommendService {
    private final LoanProductRepository loanProductRepository;
    private final MemberLoanProductRepository memberLoanProductRepository;
    private final MemberRepository memberRepository;

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

    public List<LoanProductResponseDto> recommendLoanProducts(Long memberId) {
        List<MemberLoanProduct> loanHistory = memberLoanProductRepository.findByMemberId(memberId);
        int memberCreditScore = memberRepository.findById(memberId).orElseThrow(() -> new NoRecommendedProductsException("신용점수 데이터가 없습니다.")).getCreditScore();
        if (loanHistory.isEmpty()) {
            // 대출 이력이 없는 경우
            log.info("멤버 ID {}는 대출 이력이 없습니다. 초기 사용자에게 맞는 대출 상품을 추천합니다.", memberId);

            return recommendForNewUser(memberCreditScore);
        } else {
            // 대출 이력이 있는 경우
            log.info("멤버 ID {}의 대출 이력을 분석하여 맞춤형 대출 상품을 추천합니다.", memberId);
            return recommendBasedOnLoanHistory(loanHistory, memberCreditScore);
        }
    }

    private List<LoanProductResponseDto> recommendForNewUser(Integer memberCreditScore) {
        // 기본적인 추천 로직: 신용 점수에 맞는 대출 상품 추천
        List<LoanProduct> recommendedLoans = loanProductRepository.findByRequiredCreditScoreLessThanEqual(memberCreditScore);  // 예시
        return recommendedLoans.stream()
                .map(LoanProductResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    private List<LoanProductResponseDto> recommendBasedOnLoanHistory(List<MemberLoanProduct> loanHistory, int memberCreditScore) {
        // 현재 대출 중인 상품이 있는지 확인
        boolean hasActiveLoans = loanHistory.stream()
                .anyMatch(loan -> loan.getEndDate() == null || loan.getEndDate().isAfter(LocalDate.now()));

        // 평균 상환 기간과 연체 횟수를 구함
        double averageRepaymentPeriod = loanHistory.stream()
                .mapToInt(MemberLoanProduct::getRepaymentCount)
                .average()
                .orElse(0);
        double averageLatePaymentCount = loanHistory.stream()
                .mapToInt(MemberLoanProduct::getLatePaymentCount)
                .average()
                .orElse(0);

        // 신용 점수로 대출 가능한 상품 조회
        List<LoanProduct> recommendedLoans = loanProductRepository.findByRequiredCreditScoreLessThanEqual(memberCreditScore);

        // 대출 중인 상품이 있는 경우 연체 횟수에 따라 필터링
        recommendedLoans = filterByLatePaymentCount(recommendedLoans, averageLatePaymentCount);

        // 대출 중인 상품이 없는 경우, 약간 유리한 상품 추천
        if (!hasActiveLoans) {
            BigDecimal maxInterestRate = new BigDecimal("5.50");
            recommendedLoans = recommendedLoans.stream()
                    .filter(loan -> loan.getInterestRate().compareTo(maxInterestRate) <= 0)
                    .collect(Collectors.toList());
        }

        // 평균 상환 기간을 고려하여 우선순위 부여
        List<LoanProduct> prioritizedLoans = prioritizeByRepaymentPeriod(recommendedLoans, averageRepaymentPeriod);

        // DTO로 변환하여 반환
        return prioritizedLoans.stream()
                .map(LoanProductResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    private List<LoanProduct> filterByLatePaymentCount(List<LoanProduct> loans, double averageLatePaymentCount) {
        BigDecimal minInterestRate;
        if (averageLatePaymentCount > 5) {
            minInterestRate = new BigDecimal("7.00");
        } else if (averageLatePaymentCount > 2) {
            minInterestRate = new BigDecimal("6.00");
        } else {
            minInterestRate = new BigDecimal("5.50");
        }
        return loans.stream()
                .filter(loan -> loan.getInterestRate().compareTo(minInterestRate) > 0)
                .collect(Collectors.toList());
    }

    private List<LoanProduct> prioritizeByRepaymentPeriod(List<LoanProduct> loans, double averageRepaymentPeriod) {
        return loans.stream()
                .sorted((loan1, loan2) -> {
                    boolean isLoan1Longer = loan1.getRepaymentPeriod() >= averageRepaymentPeriod;
                    boolean isLoan2Longer = loan2.getRepaymentPeriod() >= averageRepaymentPeriod;
                    return Boolean.compare(isLoan2Longer, isLoan1Longer);
                })
                .collect(Collectors.toList());
    }

}
