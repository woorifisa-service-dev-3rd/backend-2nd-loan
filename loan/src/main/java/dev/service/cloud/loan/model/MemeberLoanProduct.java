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
@Table(name = "MemberLoanProducts")
public class MemeberLoanProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "start_date")
    LocalDate startDate;
    @Column(name = "end_date")
    LocalDate endDate;
    @Column(name = "loan_amount")
    int loanAmount;
    @Column(name = "loan_due_date")
    LocalDate loanDueDate;
    @Column(name = "repayment_count")
    int repaymentCount;
    @Column(name = "late_payment_count")
    int latePaymentCount;

    @OneToMany(mappedBy = "memeberLoanProducts")
    private List<Member> members;

    @OneToMany(mappedBy = "memeberLoanProducts")
    private List<LoanProduct> loanProducts;

}
