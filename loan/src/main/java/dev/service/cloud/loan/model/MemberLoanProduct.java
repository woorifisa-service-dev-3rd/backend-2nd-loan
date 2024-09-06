package dev.service.cloud.loan.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Entity
@Table(name = "member_loan_products")
public class MemberLoanProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "loan_amount")
    private Long loanAmount;
    @Column(name = "loan_due_date")
    private LocalDate loanDueDate;
    @Column(name = "repayment_count")
    private Integer repaymentCount;
    @Column(name = "late_payment_count")
    private Integer latePaymentCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_products_id")
    private LoanProduct loanProduct;

}
