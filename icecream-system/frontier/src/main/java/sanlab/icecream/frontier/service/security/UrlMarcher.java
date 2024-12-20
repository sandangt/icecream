package sanlab.icecream.frontier.service.security;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.AntPathMatcher;
import sanlab.icecream.fundamentum.constant.ERole;

public record UrlMarcher(ERole role, String methods, String pattern) {

    public boolean match(HttpServletRequest request) {
        String path = request.getServletPath();
        String method = request.getMethod().toLowerCase();
        boolean result = new AntPathMatcher().match(pattern, path);
        if (StringUtils.isNotBlank(method)) {
            result = result && methods.contains(method);
        }
        return result;
    }

}
