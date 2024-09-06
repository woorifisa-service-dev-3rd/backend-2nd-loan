package dev.service.cloud.loan.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@AllArgsConstructor
@Table(name = "Member")
public class Members {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "email")
    String email;
    @Column(name = "phone_number")
    String phoneNumber;
    @Column(name = "address")
    String address;
    @Column(name = "registered_date")
    LocalDate registeredDate;
    @Column( name = "credit_score")
    int creditScore;
    @Column(name = "is_active")
    boolean isActive;

    @ManyToOne
    @JoinColumn(name = "memeberLoanProducts_id")
    private MemeberLoanProducts memeberLoanProducts;

}
