package sanlab.icecream.consul.service.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import sanlab.icecream.consul.exception.HttpUnauthorizedException;
import sanlab.icecream.consul.utils.SecurityContextUtils;
import sanlab.icecream.fundamentum.constant.EPreAuthorizeRole;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static sanlab.icecream.consul.exception.ConsulErrorModel.EMPTY_API_KEY;
import static sanlab.icecream.consul.exception.ConsulErrorModel.INVALID_API_KEY;

@Service
public class ApiKeyService {

    private static final String HEADER_KEY = "X-API-KEY";
    private static final String[] FILTER_PATH_START_WITH = new String[] {"/api", "/actuator"};

    @Getter
    private final Set<String> apiKeys;

    public ApiKeyService(@Value("${app.security.api-keys:}") String[] apiKeys) {
        this.apiKeys = Arrays.stream(apiKeys).map(StringUtils::trimToEmpty).collect(Collectors.toSet());
    }

    public void validateAndSetSecCtx(HttpServletRequest req) {
        String reqApiKey = StringUtils.trimToEmpty(req.getHeader(HEADER_KEY));
        if (StringUtils.isEmpty(reqApiKey)) {
            var ex = new IcRuntimeException(EMPTY_API_KEY);
            throw new HttpUnauthorizedException(ex);
        }
        if (!apiKeys.contains(reqApiKey)) {
            var ex = new IcRuntimeException(INVALID_API_KEY);
            throw new HttpUnauthorizedException(ex);
        }
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(
            new SimpleGrantedAuthority(EPreAuthorizeRole.GARDENER.getRaw())
        );
        var authentication = new UsernamePasswordAuthenticationToken(
            reqApiKey, null, authorities
        );
        SecurityContextUtils.setAuthentication(authentication);
    }

    public boolean shouldNotFilter(HttpServletRequest req) {
        String path = req.getServletPath();
        for (String pattern : FILTER_PATH_START_WITH) {
            if (path.startsWith(pattern)) return false;
        }
        return true;
    }

}
