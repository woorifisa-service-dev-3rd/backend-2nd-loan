package dev.service.cloud.loan.controller;

import dev.service.cloud.loan.dto.response.LoanProductResponseDto;
import dev.service.cloud.loan.model.LoanProduct;
import dev.service.cloud.loan.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loan-products")
@RequiredArgsConstructor
public class LoanProductController {

    private final LoanService loanService;

    @GetMapping("/loanAllItems")
    public List<LoanProductResponseDto> List(){
        //DTO 파일 생성하여 순환참조 방지
        List<LoanProductResponseDto> loanProductResponseDtos = loanService.loansearching();

        return loanProductResponseDtos;
    }

    @GetMapping("/loanAllItems/asc")
    public List<LoanProductResponseDto> ascendingAboutList(@RequestParam String data){
        //DTO 파일 생성하여 순환참조 방지
        List<LoanProductResponseDto> loanProductResponseDtos = loanService.loansearching_asc(data);

        return loanProductResponseDtos;
    }

    @GetMapping("/loanAllItems/desc")
    public List<LoanProductResponseDto> descendingAboutList(@RequestParam String data){
        //DTO 파일 생성하여 순환참조 방지
        List<LoanProductResponseDto> loanProductResponseDtos = loanService.loansearching_desc(data);

        return loanProductResponseDtos;
    }
}
