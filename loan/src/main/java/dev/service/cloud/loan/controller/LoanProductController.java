package dev.service.cloud.loan.controller;

import dev.service.cloud.loan.dto.response.LoanProductResponseDto;
import dev.service.cloud.loan.service.LoanProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan-products")
@RequiredArgsConstructor
public class LoanProductController {

    public final LoanProductService loanProductService;

    @GetMapping("/{loanProductId}")
    public ResponseEntity<LoanProductResponseDto> getLoanProductDetails(@PathVariable Long loanProductId) {
        LoanProductResponseDto loanProduct = loanProductService.findById(loanProductId);
        System.out.println(loanProduct);
        return ResponseEntity.ok(loanProduct);
    }

}
