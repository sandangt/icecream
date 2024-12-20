package sanlab.icecream.frontier.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class CrossOriginResourceFilter extends OncePerRequestFilter {

    private final List<String> allowedOriginList;

    public CrossOriginResourceFilter(@Value("${app.cors.allowed-origins}") List<String> allowedOrigins) {
        allowedOriginList = allowedOrigins;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        String origin = request.getHeader("origin");
        if (isAllowedOrigin(origin)) {
            response.addHeader("Access-Control-Allow-Origin", origin);
            response.addHeader("Access-Control-Allow-Methods", "POST, GET, HEAD, OPTIONS, DELETE, PUT" );
            response.addHeader("Access-Control-Allow-Headers", "*");
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Max-Age", "3600" );
            response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        }
        response.addHeader("Cache-Control", "no-cache");
        filterChain.doFilter(request, response);
    }

    private boolean isAllowedOrigin(String origin) {
        return allowedOriginList.contains(origin);
    }

}
