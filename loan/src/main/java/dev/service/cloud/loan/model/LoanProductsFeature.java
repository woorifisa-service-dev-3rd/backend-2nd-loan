package dev.service.cloud.loan.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Entity
@Table(name = "LoanProductsFeatures")
public class LoanProductsFeature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "loanProductsFeatures")
    private List<LoanProduct> loanProducts;
}
