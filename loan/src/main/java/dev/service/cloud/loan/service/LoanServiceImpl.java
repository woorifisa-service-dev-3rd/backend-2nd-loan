package dev.service.cloud.loan.service;

import dev.service.cloud.loan.dto.request.LoanRequestDto;
import dev.service.cloud.loan.dto.response.LoanResponseDto;
import dev.service.cloud.loan.exception.ErrorCode;
import dev.service.cloud.loan.exception.LoanException;
import dev.service.cloud.loan.model.LoanProduct;
import dev.service.cloud.loan.model.Member;
import dev.service.cloud.loan.model.MemberLoanProduct;
import dev.service.cloud.loan.repository.LoanProductRepository;
import dev.service.cloud.loan.repository.MemberLoanProductRepository;
import dev.service.cloud.loan.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final MemberLoanProductRepository memberLoanProductRepository;
    private final MemberRepository memberRepository;
    private final LoanProductRepository loanProductRepository;

    public List<LoanResponseDto> findAllLoans() {
        List<MemberLoanProduct> all = memberLoanProductRepository.findAll();
        return LoanResponseDto.toDtos(all);
    }

    @Override
    @Transactional
    public LoanResponseDto addNewLoan(LoanRequestDto loanRequestDto) {
        Member member = memberRepository.findById(loanRequestDto.getMemberId()).orElseThrow(() -> new LoanException(ErrorCode.MEMBER_NOT_FOUND, "회원 아이디 : " + loanRequestDto.getMemberId()));
        LoanProduct loanProduct = loanProductRepository.findById(loanRequestDto.getLoanProductId()).orElseThrow(() -> new LoanException(ErrorCode.LOAN_PRODUCT_NOT_FOUND, "대출 상품 아이디 : " + loanRequestDto.getLoanProductId()));
        long loanAmount = loanRequestDto.getLoanAmount();
        LocalDate loanDueDate = LocalDate.now().plusMonths(loanProduct.getRepaymentPeriod());

        if(isAvailToLoan(member.getCreditScore(), loanProduct.getRequiredCreditScore(), loanProduct.getMaxLimit(), loanAmount)) {
            MemberLoanProduct memberLoanProduct = MemberLoanProduct.createMemberLoanProduct(member, loanProduct, loanAmount, loanDueDate);

            memberLoanProductRepository.save(memberLoanProduct);

            return LoanResponseDto.toDto(memberLoanProduct);
        }

        throw new LoanException(ErrorCode.LOAN_CONDITION_NOT_MATCH, "");
    }

    private boolean isAvailToLoan(int memberCreditScore, int requiredCreditScore, int loanMaxLimit, long loanAmount) {
        if(memberCreditScore < requiredCreditScore) {
            throw new LoanException(ErrorCode.LOW_CREDIT_SCORE, "신용점수 : " + memberCreditScore + " 필요한 신용점수 : " + requiredCreditScore);
        }

        if(loanAmount > loanMaxLimit) {
            throw new LoanException(ErrorCode.OVER_MAX_LIMIT_LOAN_AMOUNT, "신청 금액 : " + loanAmount + " 최대 대출 한도 : " + loanMaxLimit);
        }

        return true;
    }
}
