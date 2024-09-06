package dev.service.cloud.loan.controller;

import dev.service.cloud.loan.dto.response.LoanResponseDto;
import dev.service.cloud.loan.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanResponseDto>> findAllLoans() {
        List<LoanResponseDto> allLoans = loanService.findAllLoans();
        return ResponseEntity.ok(allLoans);
    }
}
