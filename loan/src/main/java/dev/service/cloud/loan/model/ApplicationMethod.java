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
@Table(name = "ApplicationMethod")
public class ApplicationMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "applicationMethod")
    private List<LoanProducts> loanProducts;

}
