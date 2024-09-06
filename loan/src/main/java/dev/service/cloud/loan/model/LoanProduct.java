package dev.service.cloud.loan.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Entity
@Table(name = "loan_products")
public class LoanProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    @CreationTimestamp
    private BigDecimal interestRate;

    @Column(name = "max_limit")
    private Integer maxLimit;

    @Column(name = "repayment_period")
    private LocalDate repaymentPeriod;

    @Column(name = "required_credit_score")
    private Integer requiredCreditScore;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private LoanProductsType loanProductsType;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "loan_products_feature_id")
    private LoanProductsFeature loanProductsFeature;

    @ManyToOne
    @JoinColumn(name = "application_method_id")
    private ApplicationMethod applicationMethod;

    @OneToMany(mappedBy = "loanProduct")
    @Builder.Default
    private List<MemberLoanProduct> memberLoanProducts = new ArrayList<>();

}
