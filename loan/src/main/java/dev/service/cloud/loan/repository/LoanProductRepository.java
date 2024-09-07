package dev.service.cloud.loan.repository;

import dev.service.cloud.loan.model.LoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanProductRepository extends JpaRepository<LoanProduct, Long> {
    /**
     * creditScore : 신용점수 or 조사점수
     * 전달받은 점수를 기준으로 신청 가능한 상품중 대출중인 상품들을
     * 최대한도 오름차순, 이자율 오름차순으로 정렬헤서 꺼내온다
     *
     * @param point
     * @return List<LoanProduct> sorted
     */
    @Query(value = "SELECT * FROM loan_products lp WHERE lp.required_credit_score <= :creditScore AND CURRENT_DATE <= lp.end_date ORDER BY lp.max_limit ASC, lp.interest_rate ASC LIMIT 10",
            nativeQuery = true)
    List<LoanProduct> findEligibleLoanProducts(@Param("creditScore") int point);

    /**
     * 회원 대출 이력이 없는 경우 신용점수 기준으로 상품을 추천
     * @param memberCreditSocre
     * @return
     */
    List<LoanProduct> findByRequiredCreditScoreLessThanEqual(Integer memberCreditSocre);
}
