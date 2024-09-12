package dev.service.cloud.loan.controller;

import dev.service.cloud.loan.dto.response.LoanProductResponseDto;
import dev.service.cloud.loan.service.LoanProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan-products")
@RequiredArgsConstructor
public class LoanProductController {

    private final LoanProductService loanProductService;

    @GetMapping
    public List<LoanProductResponseDto> List(@RequestParam(value = "sort") String sort, @RequestParam(value = "data") String data) {
        //DTO 파일 생성하여 순환참조 방지
        List<LoanProductResponseDto> loanProductResponseDtos = loanProductService.searchLoans(sort, data);

        return loanProductResponseDtos;
    }

    @GetMapping("/{loanProductId}")
    public ResponseEntity<LoanProductResponseDto> getLoanProductDetails(@PathVariable Long loanProductId) {
        LoanProductResponseDto loanProduct = loanProductService.findById(loanProductId);
        System.out.println(loanProduct);
        return ResponseEntity.ok(loanProduct);
    }

}
