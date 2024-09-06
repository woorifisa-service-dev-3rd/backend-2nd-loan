package dev.service.cloud.loan.controller;

import dev.service.cloud.loan.customexception.NoRecommendedProductsException;
import dev.service.cloud.loan.dto.response.LoanProductResponseDto;
import dev.service.cloud.loan.service.RecommendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/loan-products")
@RequiredArgsConstructor
@Slf4j
public class RecommendController {
    private final RecommendService recommendService;

    @GetMapping()
    @ResponseBody
    public List<LoanProductResponseDto> AllList() {
        return recommendService.findAll();
    }

    @GetMapping("/recommend/{point}")
    public ResponseEntity<?> recommendByPointLoanProducts(@PathVariable int point) {
        int limit=10;
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
}
