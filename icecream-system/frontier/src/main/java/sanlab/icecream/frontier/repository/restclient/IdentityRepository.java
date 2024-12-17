package sanlab.icecream.frontier.repository.restclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import sanlab.icecream.frontier.dto.core.RegisteredUserInfoDto;

import java.util.Optional;

@Component
public record IdentityRepository(RestClient restClient,
                                 @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}") String identityUrl) {

    public Optional<RegisteredUserInfoDto> getRegisteredUserInfo() {
        var result = restClient.get()
            .uri(identityUrl)
            .retrieve()
            .body(RegisteredUserInfoDto.class);
        return Optional.ofNullable(result);
    }
}
