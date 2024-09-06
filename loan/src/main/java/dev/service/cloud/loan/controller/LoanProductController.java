package dev.service.cloud.loan.controller;

import dev.service.cloud.loan.model.LoanProduct;
import dev.service.cloud.loan.service.LoanProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loan-products")
@RequiredArgsConstructor
public class LoanProductController {

    public final LoanProductService loanProductService;

    @GetMapping("/{loanProductId}")
    public String getLoanProductDetails(@PathVariable Long loanProductId){
       List<LoanProduct> loanProduct = loanProductService.findAllLoanProducts();
        System.out.println(loanProduct);
        return null;
    }

}
