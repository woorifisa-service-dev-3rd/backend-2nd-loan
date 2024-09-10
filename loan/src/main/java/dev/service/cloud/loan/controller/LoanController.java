package dev.service.cloud.loan.controller;

import dev.service.cloud.loan.dto.response.LoanProductResponseDto;
import dev.service.cloud.loan.model.LoanProduct;
import dev.service.cloud.loan.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping
    public ModelAndView test1(LoanProduct loanProduct){
        ModelAndView mvn = new ModelAndView();
        List<LoanProductResponseDto> loanProductResponseDtos = loanService.loansearching();
        System.out.println(loanProductResponseDtos);
//        loanService.test1();
        mvn.setViewName("/loanList");
        mvn.addObject("listOwners",loanProductResponseDtos);
        return mvn;
    }
}
