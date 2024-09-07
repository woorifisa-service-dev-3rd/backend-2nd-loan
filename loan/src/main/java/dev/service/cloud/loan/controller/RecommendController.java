package dev.service.cloud.loan.controller;

import dev.service.cloud.loan.customexception.NoRecommendedProductsException;
import dev.service.cloud.loan.dto.response.LoanProductResponseDto;
import dev.service.cloud.loan.service.RecommendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

//    @GetMapping("/recommend/{point}")
//    @ResponseBody
//    public ResponseEntity<?> recommendByPointLoanProducts(@PathVariable int point) {
//        try {
//            List<LoanProductResponseDto> recommendedProducts = recommendService.recommendByPoint(point);
//            return ResponseEntity.ok(recommendedProducts);
//        } catch (NoRecommendedProductsException e) {
//            // 추천 상품이 없을 때 404 반환
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        } catch (Exception e) {
//            // 기타 예외 처리
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
//        }
//    }

    @GetMapping("/recommend/{point}")
    public String recommendByPointLoanProducts(@PathVariable int point, Model model) {
        try {
            List<LoanProductResponseDto> recommendedProducts = recommendService.recommendByPoint(point);
            model.addAttribute("loanProducts", recommendedProducts);
        } catch (NoRecommendedProductsException e) {
            model.addAttribute("message", "추천할 대출 상품이 없습니다.");
        } catch (Exception e) {
            model.addAttribute("message", "서버 오류가 발생했습니다.");
        } finally {
            return "loan-products-view";
        }
    }

    //    @GetMapping("/recommend/m/{memberId}")
//    @ResponseBody
//    public ResponseEntity<List<LoanProductResponseDto>> getRecommendforMember(@PathVariable Long memberId) {
//        List<LoanProductResponseDto> recommendations = recommendService.recommendLoanProductsforMember(memberId);
//        return ResponseEntity.ok(recommendations);
//    }
    @GetMapping("/recommend/m/{memberId}")
    public String getRecommendforMember(@PathVariable Long memberId, Model model) {
        try {
            List<LoanProductResponseDto> recommendations = recommendService.recommendLoanProductsforMember(memberId);
            model.addAttribute("loanProducts", recommendations);
        } catch (NoRecommendedProductsException e) {
            model.addAttribute("message", "추천할 대출 상품이 없습니다.");
        } catch (Exception e) {
            model.addAttribute("message", "서버 오류가 발생했습니다.");
        } finally {
            return "loan-products-view";
        }
    }
}
