package dev.service.cloud.loan.controller;

import dev.service.cloud.loan.dto.response.LoanProductResponseDto;
import dev.service.cloud.loan.service.RecommendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/loan-products")
@RequiredArgsConstructor
@Slf4j
public class RecommendController {
    private final RecommendService recommendService;

    @GetMapping()
    public List<LoanProductResponseDto> AllList() {
        System.out.println(recommendService.findAll());
        return null;
    }

    @GetMapping("/recommend/{point}")
    public List<LoanProductResponseDto> recommendByPointLoanProducts(@PathVariable int point) throws Exception {
//        전체 조회
        List<LoanProductResponseDto> recommendedProducts = recommendService.findAll();


        // 추천 상품 목록을 반환

        return null;
    }
}
