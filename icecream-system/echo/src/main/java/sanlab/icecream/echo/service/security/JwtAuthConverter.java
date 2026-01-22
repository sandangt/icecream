package sanlab.icecream.echo.service.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import sanlab.icecream.echo.dto.core.RegisteredUserInfoDto;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;
import sanlab.icecream.fundamentum.utils.LogUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static sanlab.icecream.echo.exception.EchoErrorModel.SECURITY_JWT_INVALID;

@Service
@Slf4j
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final String principleAttribute;
    private final JwtDecoder jwtDecoder;

    public JwtAuthConverter(
        @Value("${app.security.jwt.converter.principle-attribute}") String principleAttribute,
        JwtDecoder jwtDecoder
    ) {
        this.principleAttribute = principleAttribute;
        this.jwtDecoder = jwtDecoder;
    }

    public AbstractAuthenticationToken convertStr(@NonNull String source) {
        String tokenStr = source;
        if (source.startsWith("Bearer")) {
            tokenStr = source.replace("Bearer ", StringUtils.EMPTY);
        }
        try {
            Jwt token = jwtDecoder.decode(tokenStr);
            return convert(token);
        } catch (Exception ex) {
            LogUtils.logException(log, ex);
            throw new IcRuntimeException(SECURITY_JWT_INVALID, "Failed to parse websocket jwt");
        }
    }

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt source) {
        Collection<GrantedAuthority> authorities =
            new HashSet<>(extractResourceRoles(source));
        return new JwtAuthenticationToken(
            source,
            authorities,
            getPrincipleClaimName(source)
        ) {
            @Override
            public Object getPrincipal() {
                return RegisteredUserInfoDto.builder()
                    .sub(source.getClaims().getOrDefault("sub", StringUtils.EMPTY).toString())
                    .emailVerified(source.getClaims().getOrDefault("email", StringUtils.EMPTY).toString())
                    .email(source.getClaims().getOrDefault("email", StringUtils.EMPTY).toString())
                    .name(source.getClaims().getOrDefault("name", StringUtils.EMPTY).toString())
                    .preferredUsername(source.getClaims().getOrDefault("preferred_username", StringUtils.EMPTY).toString())
                    .givenName(source.getClaims().getOrDefault("given_name", StringUtils.EMPTY).toString())
                    .familyName(source.getClaims().getOrDefault("family_name", StringUtils.EMPTY).toString())
                    .authorities(authorities)
                    .build();
            }
        };
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        Map<String, Object> resourceAccess;
        Collection<String> resourceRoles;
        if (jwt.getClaim("realm_access") == null) {
            return Set.of();
        }
        resourceAccess = jwt.getClaim("realm_access");
        resourceRoles = (Collection<String>) resourceAccess.get("roles");
        return resourceRoles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
            .collect(Collectors.toSet());
    }

    private String getPrincipleClaimName(Jwt jwt) {
        String claimName = JwtClaimNames.SUB;
        if (principleAttribute != null) {
            claimName = principleAttribute;
        }
        return jwt.getClaim(claimName);
    }
}

