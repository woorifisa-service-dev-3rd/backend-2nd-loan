package dev.service.cloud.loan.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Entity
@Table(name = "provider")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "provider")
    @Builder.Default
    private List<LoanProduct> loanProducts = new ArrayList<>();
}
