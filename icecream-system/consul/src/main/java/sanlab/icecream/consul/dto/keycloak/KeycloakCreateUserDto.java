package sanlab.icecream.consul.dto.keycloak;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_EMPTY)
public class KeycloakCreateUserDto {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean emailVerified;
    private Boolean enabled;
    private List<KeycloakCredentialType> credentials;

    public record KeycloakCredentialType(String type, String value, Boolean temporary) {}
    public record Tidy(String username, String firstName, String lastName, String email, String password) {}

    public static KeycloakCreateUserDto extractFromTidyUnverified(Tidy payload) {
        return KeycloakCreateUserDto.builder()
            .username(payload.username())
            .firstName(payload.firstName())
            .lastName(payload.lastName())
            .email(payload.email())
            .emailVerified(false)
            .enabled(true)
            .credentials(Collections.singletonList(
                new KeycloakCredentialType("password", payload.password(), false)
            ))
            .build();
    }

    public static KeycloakCreateUserDto extractFromTidyVerified(Tidy payload) {
        return KeycloakCreateUserDto.builder()
            .username(payload.username())
            .firstName(payload.firstName())
            .lastName(payload.lastName())
            .email(payload.email())
            .emailVerified(true)
            .enabled(true)
            .credentials(Collections.singletonList(
                new KeycloakCredentialType("password", payload.password(), false)
            ))
            .build();
    }

}
