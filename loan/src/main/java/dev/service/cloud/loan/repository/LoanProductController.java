package dev.service.cloud.loan.repository;

import dev.service.cloud.loan.model.LoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanProductController extends JpaRepository<LoanProduct, Long> {
}
