package dev.service.cloud.loan.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@AllArgsConstructor
@Table(name = "LoanProducts")
public class LoanProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "start_date")
    LocalDate startDate;
    @Column(name = "end_date")
    LocalDate endDate;
    @Column(name = "interest_rate")
    int interestRate;
    @Column(name = "max_limit")
    int MaxLimit;
    @Column(name = "repayment_period")
    LocalDate repaymentPeriod;
    @Column(name = "required_credit_score")
    int requiredCreditScore;

    @ManyToOne
    @JoinColumn(name = "LoanProductsType_id")
    private LoanProductsType loanProductsType;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "loan_products_feature_id")
    private LoanProductsFeatures loanProductsFeatures;

    @ManyToOne
    @JoinColumn(name = "application_method_id")
    private ApplicationMethod applicationMethod;

    @ManyToOne
    @JoinColumn(name = "memeber_loan_products_id")
    private MemeberLoanProducts memeberLoanProducts;


}
