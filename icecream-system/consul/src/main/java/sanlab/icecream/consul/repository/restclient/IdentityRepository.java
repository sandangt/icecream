package sanlab.icecream.consul.repository.restclient;

import sanlab.icecream.consul.dto.keycloak.KeycloakCreateUserDto;
import sanlab.icecream.consul.dto.keycloak.TokenResponseDto;
import sanlab.icecream.consul.dto.keycloak.KeycloakUpdateUserInfoDto;

import java.util.Optional;
import java.util.UUID;

public interface IdentityRepository {

    TokenResponseDto adminLogin();

    Optional<KeycloakUpdateUserInfoDto> getUserInfoByUserId(UUID userId);

    void updateUserInfoByUserId(UUID userId, KeycloakUpdateUserInfoDto payload);

    void createUserUnverified(KeycloakCreateUserDto.Tidy payload);

}
