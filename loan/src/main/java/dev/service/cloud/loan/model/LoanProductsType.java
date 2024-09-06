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
@Table(name = "LoanProductsType")
public class LoanProductsType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "loanProductsType")
    private List<LoanProducts> loanProducts;
}
