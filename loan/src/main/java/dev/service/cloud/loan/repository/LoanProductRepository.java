package dev.service.cloud.loan.repository;

import dev.service.cloud.loan.model.LoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanProductRepository extends JpaRepository<LoanProduct, Long> {
    @Query(value =
            "SELECT lp" +
                    "lp.loanProductsType, " +
                    "lp.interestRate, " +
                    "lp.maxLimit, " +
                    "lp.repaymentPeriod, " +
                    "lpf.name, " +  // 상품특징 이름을 가져오기 위해
                    "am.name, " +   // 대출신청방법 이름을 가져오기 위해
                    "p.name, " +    // 제공사 이름을 가져오기 위해
                    "lp.requiredCreditScore) " +
                    "FROM LoanProduct lp " +
                    "LEFT JOIN lp.provider p " +
                    "LEFT JOIN lp.loanProductsType lpt " +
                    "LEFT JOIN lp.loanProductsFeature lpf " +
                    "LEFT JOIN lp.applicationMethod am " +
                    "WHERE lp.id = :loanId")
        // loanId로 조회
    List<LoanProduct> findLoanProductById(@Param("loanId") Long loanId);

    @Override
    List<LoanProduct> findAll();
}
