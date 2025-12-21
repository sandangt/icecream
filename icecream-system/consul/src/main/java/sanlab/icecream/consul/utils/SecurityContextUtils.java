package sanlab.icecream.consul.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import sanlab.icecream.consul.dto.core.RegisteredUserInfoDto;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.Optional;

import static sanlab.icecream.consul.exception.ConsulErrorModel.INVALID_USER_PRINCIPAL;

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
        return registeredUserInfo().orElseThrow(() -> new IcRuntimeException(INVALID_USER_PRINCIPAL));
    }

    public static boolean isLoggedIn() {
        return registeredUserInfo().isPresent();
    }

}
