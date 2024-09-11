package dev.service.cloud.loan.controller;


import dev.service.cloud.loan.dto.response.LoanProductResponseDto;
import dev.service.cloud.loan.exception.NoRecommendedProductsException;
import dev.service.cloud.loan.service.RecommendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan-products")
@RequiredArgsConstructor
@Slf4j
public class RecommendController {
    private final RecommendService recommendService;


    @GetMapping("/recommend/{point}")
    public ResponseEntity<?> recommendByPointLoanProducts(@PathVariable int point) {
        try {
            List<LoanProductResponseDto> recommendedProducts = recommendService.recommendByPoint(point);
            return ResponseEntity.ok(recommendedProducts);
        } catch (NoRecommendedProductsException e) {
            // 추천 상품이 없을 때 404 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            // 기타 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
        }
    }


    @GetMapping("/recommend/member/{memberId}")
    public ResponseEntity<List<LoanProductResponseDto>> getRecommendforMember(@PathVariable Long memberId) {
        List<LoanProductResponseDto> recommendations = recommendService.recommendLoanProductsforMember(memberId);
        return ResponseEntity.ok(recommendations);
    }


}
