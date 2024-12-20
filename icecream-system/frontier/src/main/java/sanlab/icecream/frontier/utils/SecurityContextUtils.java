package sanlab.icecream.frontier.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import sanlab.icecream.frontier.dto.core.RegisteredUserInfoDto;
import sanlab.icecream.frontier.exception.InvalidUserPrincipalException;

import java.util.Optional;

public class SecurityContextUtils {

    private SecurityContextUtils() {}

    private static Optional<RegisteredUserInfoDto> registeredUserInfo() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
            .map(SecurityContext::getAuthentication)
            .map(Authentication::getPrincipal)
            .filter(RegisteredUserInfoDto.class::isInstance)
            .map(RegisteredUserInfoDto.class::cast);
    }

    public static Optional<RegisteredUserInfoDto> getRegisteredUserInfo() {
        var userDetailsOptional = registeredUserInfo();

        if (userDetailsOptional.isEmpty()) {
            throw InvalidUserPrincipalException.defaultInstance();
        }

        return userDetailsOptional;
    }

    public static boolean isLoggedIn() {
        return registeredUserInfo().isPresent();
    }

}
