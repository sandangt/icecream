package sanlab.icecream.consul.repository.restclient.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import sanlab.icecream.consul.dto.core.RegisteredUserInfoDto;
import sanlab.icecream.consul.repository.restclient.IIdentityRepository;

import java.util.Optional;

@Component
public class IdentityRepositoryImpl implements IIdentityRepository {

    private final RestClient restClient;
    private final String identityUrl;

    public IdentityRepositoryImpl(
        RestClient restClient,
        @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}") String identityUrl) {
        this.restClient = restClient;
        this.identityUrl = identityUrl;
    }

    @Override
    public Optional<RegisteredUserInfoDto> getRegisteredUserInfo() {
        var result = restClient.get()
            .uri(identityUrl)
            .retrieve()
            .body(RegisteredUserInfoDto.class);
        return Optional.ofNullable(result);
    }

}
