package dev.service.cloud.loan.repository;

import dev.service.cloud.loan.model.LoanProduct;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanProductRepository extends JpaRepository<LoanProduct, Long> {

    @EntityGraph(attributePaths = {"loanProductsFeature", "applicationMethod", "loanProductsType", "provider"})
    Optional<LoanProduct> findById(Long loanId);
}
