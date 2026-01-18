package sanlab.icecream.consul.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import sanlab.icecream.consul.dto.core.RegisteredUserInfoDto;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.Optional;

import static sanlab.icecream.consul.exception.ConsulErrorModel.SECURITY_USER_PRINCIPAL_INVALID;

public final class SecurityContextUtils {

    private SecurityContextUtils() {}

    private static Optional<RegisteredUserInfoDto> registeredUserInfo() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
            .map(SecurityContext::getAuthentication)
            .map(Authentication::getPrincipal)
            .filter(RegisteredUserInfoDto.class::isInstance)
            .map(RegisteredUserInfoDto.class::cast);
    }

    public static Optional<RegisteredUserInfoDto> getRegisteredUserInfoOptional() {
        return registeredUserInfo();
    }

    public static RegisteredUserInfoDto getRegisteredUserInfo() {
        return registeredUserInfo().orElseThrow(() -> new IcRuntimeException(SECURITY_USER_PRINCIPAL_INVALID));
    }

    public static boolean isLoggedIn() {
        return registeredUserInfo().isPresent();
    }

    public static void setAuthentication(Authentication authentication) {
        Optional.ofNullable(authentication)
            .ifPresent(inner -> SecurityContextHolder.getContext().setAuthentication(inner));
    }

}
