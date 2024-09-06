package dev.service.cloud.loan.repository;

import dev.service.cloud.loan.model.LoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanProductRepository extends JpaRepository<LoanProduct, Long> {
    /**
     * creditScore : 신용점수 or 조사점수
     *
     * @param creditScore
     * @return
     */
    @Query("SELECT lp FROM LoanProduct lp WHERE lp.requiredCreditScore <= :creditScore AND CURRENT_DATE <= lp.endDate")
    List<LoanProduct> findEligibleLoanProducts(@Param("creditScore") int creditScore);

}
