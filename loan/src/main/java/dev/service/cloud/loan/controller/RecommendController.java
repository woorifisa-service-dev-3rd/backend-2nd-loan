package dev.service.cloud.loan.controller;

import dev.service.cloud.loan.dto.LoanProductResponseDto;
import dev.service.cloud.loan.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/loan-products")
@RequiredArgsConstructor
public class RecommendController {
    private final RecommendService recommendService;
    @GetMapping("/recommend/{point}")
    public List<LoanProductResponseDto> recommendLoanProducts(@RequestParam("point") int point) {
        // 고객 점수 기반으로 추천 상품 목록 조회



        // 추천 상품 목록을 반환

        return null;
    }
}
