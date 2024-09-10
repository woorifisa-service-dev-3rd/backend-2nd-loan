package dev.service.cloud.loan.dto.response;

import dev.service.cloud.loan.model.Provider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
@AllArgsConstructor
public class ProviderResponse {
    private Long id;
    private String name;

    public static ProviderResponse from(Provider provider) {
        return ProviderResponse.builder().id(provider.getId()).name(provider.getName()).build();
    }
}
