package dev.service.cloud.loan.dto.response;

import dev.service.cloud.loan.model.LoanProductsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TypeResponseDto {
    private Long id;
    private String name;

    public static TypeResponseDto toDto(LoanProductsType type) {
        return TypeResponseDto.builder().id(type.getId()).name(type.getName()).build();
    }
}
