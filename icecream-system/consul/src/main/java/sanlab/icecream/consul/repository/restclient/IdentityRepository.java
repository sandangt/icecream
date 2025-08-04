package sanlab.icecream.consul.repository.restclient;

import sanlab.icecream.consul.dto.keycloak.TokenResponseDto;
import sanlab.icecream.consul.dto.keycloak.KeycloakUserInfoDto;

import java.util.Optional;
import java.util.UUID;

public interface IdentityRepository {

    TokenResponseDto adminLogin();

    Optional<KeycloakUserInfoDto> getUserInfoByUserId(UUID userId);

    void updateUserInfoByUserId(UUID userId, KeycloakUserInfoDto payload);

}
