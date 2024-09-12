package dev.service.cloud.loan.controller;

import dev.service.cloud.loan.dto.response.LoanProductResponseDto;
import dev.service.cloud.loan.service.LoanProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loan-products")
@RequiredArgsConstructor
public class LoanProductController {

    private final LoanProductService loanProductService;

    @GetMapping
    public List<LoanProductResponseDto> List(@RequestParam(value = "filterName", required = false) String filterName, @RequestParam(value = "conditionName", required = false) String conditionName){

        List<LoanProductResponseDto> loanProductResponseDtos = loanProductService.searchLoansByCondition(filterName,conditionName);
        return loanProductResponseDtos;
    }

}
