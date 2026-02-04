package sanlab.icecream.consul.dto.keycloak;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_EMPTY)
public class KeycloakUpdateUserInfoDto {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean emailVerified;
    private Long createdTimestamp;
    private Boolean enabled;

}
