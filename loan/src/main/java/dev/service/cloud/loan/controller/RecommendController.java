package dev.service.cloud.loan.controller;

import dev.service.cloud.loan.dto.LoanProductResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/loan-products")
public class RecommendController {

    @GetMapping("/recommend")
    public List<LoanProductResponseDto> recommendLoanProducts(){
        // 고객 정보 조회

        // 고객 정보를 기반으로 추천 상품 목록 조회

        // 추천 상품 목록을 반환

        return null;
    }
}
