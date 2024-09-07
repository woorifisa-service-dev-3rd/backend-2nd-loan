package dev.service.cloud.loan.controller;

import dev.service.cloud.loan.dto.response.LoanResponseDto;
import dev.service.cloud.loan.service.LoanService;
import dev.service.cloud.loan.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final LoanService loanService;

    @RequestMapping("/{id}")
    public List<LoanResponseDto> getMemberLoanlist(@PathVariable("id") Long memberId, Model model) {
        loanService.getLoanListByMemberId(memberId);
        return List.of();
    }
}
