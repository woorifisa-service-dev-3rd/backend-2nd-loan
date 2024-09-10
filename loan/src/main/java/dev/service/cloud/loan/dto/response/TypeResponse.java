package dev.service.cloud.loan.dto.response;

import dev.service.cloud.loan.model.LoanProductsType;
import dev.service.cloud.loan.model.Provider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
@AllArgsConstructor
public class TypeResponse {
    private Long id;
    private String name;

    public static TypeResponse from(LoanProductsType type) {
        return TypeResponse.builder().id(type.getId()).name(type.getName()).build();
    }
}
