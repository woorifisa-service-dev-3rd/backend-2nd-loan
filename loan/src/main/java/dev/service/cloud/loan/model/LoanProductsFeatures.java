package dev.service.cloud.loan.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@AllArgsConstructor
@Table(name = "LoanProductsFeatures")
public class LoanProductsFeatures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "loanProductsFeatures")
    private List<LoanProducts> loanProducts;
}
