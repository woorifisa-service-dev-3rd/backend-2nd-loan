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
@Table(name = "LoanProductsType")
public class LoanProductsType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "loanProductsType")
    private List<LoanProduct> loanProducts;
}
