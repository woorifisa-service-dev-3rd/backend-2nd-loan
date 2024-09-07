package dev.service.cloud.loan.repository;

import dev.service.cloud.loan.model.MemberLoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberLoanProductRepository extends JpaRepository<MemberLoanProduct, Long> {
    List<MemberLoanProduct> findByMemberId(Long memberId);
}
