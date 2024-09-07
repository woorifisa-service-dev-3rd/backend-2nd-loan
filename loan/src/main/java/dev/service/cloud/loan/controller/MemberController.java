package dev.service.cloud.loan.controller;

import dev.service.cloud.loan.dto.response.LoanResponseDto;
import dev.service.cloud.loan.service.LoanService;
import dev.service.cloud.loan.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final LoanService loanService;


    /**
     * 회원의 대출 목록 조회
     * @param memberId
     * @param model
     * @return List<LoanResponseDto> 해당 회원의 대출 목록 반환
     */
    @GetMapping("{id}/loans")
    public String getMemberLoanlist(@PathVariable("id") Long memberId, Model model) {
        model.addAttribute("loanList",loanService.getLoanListByMemberId(memberId));
        return "memeber_loanList";
    }
}
