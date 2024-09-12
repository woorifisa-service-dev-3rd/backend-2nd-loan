package dev.service.cloud.loan.repository;

import dev.service.cloud.loan.model.MemberLoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLoanProductRepository extends JpaRepository<MemberLoanProduct, Long> {
}
