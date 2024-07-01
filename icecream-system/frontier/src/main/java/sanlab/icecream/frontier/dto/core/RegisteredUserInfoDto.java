package sanlab.icecream.frontier.dto.core;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisteredUserInfoDto {
    private String sub;
    private String emailVerified;
    private String name;
    private String preferredUsername;
    private String givenName;
    private String familyName;
    private String email;
}
