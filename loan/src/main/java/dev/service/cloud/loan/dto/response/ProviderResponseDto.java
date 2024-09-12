package dev.service.cloud.loan.dto.response;

import dev.service.cloud.loan.model.Provider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProviderResponseDto {
    private Long id;
    private String name;

    public static ProviderResponseDto toDto(Provider provider) {
        return ProviderResponseDto.builder().id(provider.getId()).name(provider.getName()).build();
    }
}
