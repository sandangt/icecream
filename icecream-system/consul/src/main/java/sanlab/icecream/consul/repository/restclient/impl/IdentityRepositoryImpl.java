package sanlab.icecream.consul.repository.restclient.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import sanlab.icecream.consul.dto.keycloak.TokenResponseDto;
import sanlab.icecream.consul.dto.keycloak.KeycloakUserInfoDto;
import sanlab.icecream.consul.repository.restclient.IdentityRepository;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.IDENTITY_IDENTITY_ADMIN_TOKEN_FAILED;
import static sanlab.icecream.consul.exception.ConsulErrorModel.IDENTITY_UPDATE_USER_INFO_REQUEST_FAILED;
import static sanlab.icecream.consul.exception.ConsulErrorModel.IDENTITY_IDENTITY_SERVICE_UNAVAILABLE;

@Component
public class IdentityRepositoryImpl implements IdentityRepository {

    private static final String BEARER_TOKEN = "Bearer %s";

    private final RestClient identityClient;
    private final String adminUsername;
    private final String adminPassword;
    private final String realm;

    public IdentityRepositoryImpl(
        RestClient identityClient,
        @Value("${app.identity.admin.username}") String adminUsername,
        @Value("${app.identity.admin.password}") String adminPassword,
        @Value("${app.identity.realm}") String realm
    ) {
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.identityClient = identityClient;
        this.realm = realm;
    }

    @Override
    public TokenResponseDto adminLogin() {
        Map<String, String> formData = Map.of(
            "grant_type", "password",
            "client_id", "admin-cli",
            "username", this.adminUsername,
            "password", this.adminPassword
        );
        try {
            return identityClient.post()
                .uri("/realms/master/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(MultiValueMap.fromSingleValue(formData))
                .retrieve()
                .body(TokenResponseDto.class);
        } catch (HttpClientErrorException ex) {
            throw new IcRuntimeException(ex, IDENTITY_IDENTITY_ADMIN_TOKEN_FAILED);
        }
    }

    @Override
    public Optional<KeycloakUserInfoDto> getUserInfoByUserId(UUID userId) {
        var adminToken = adminLogin();
        try {
            var response = identityClient.get()
                .uri("/admin/realms/%s/users/%s".formatted(this.realm, userId))
                .header("Authorization", BEARER_TOKEN.formatted(adminToken.getAccessToken()))
                .retrieve()
                .body(KeycloakUserInfoDto.class);
            return Optional.ofNullable(response);
        } catch (HttpClientErrorException ex) {
            return Optional.empty();
        }
    }

    @Override
    public void updateUserInfoByUserId(UUID userId, KeycloakUserInfoDto payload) {
        var adminToken = adminLogin();
        identityClient.put()
            .uri("/admin/realms/%s/users/%s".formatted(this.realm, userId))
            .header("Authorization", BEARER_TOKEN.formatted(adminToken.getAccessToken()))
            .body(payload)
            .contentType(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                throw new IcRuntimeException(IDENTITY_UPDATE_USER_INFO_REQUEST_FAILED);
            })
            .onStatus(HttpStatusCode::is5xxServerError, (req, res) -> {
                throw new IcRuntimeException(IDENTITY_IDENTITY_SERVICE_UNAVAILABLE);
            })
            .toBodilessEntity();
    }
}
