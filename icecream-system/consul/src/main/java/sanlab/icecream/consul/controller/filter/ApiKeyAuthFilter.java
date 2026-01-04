package sanlab.icecream.consul.controller.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import sanlab.icecream.consul.service.security.ApiKeyService;

import java.io.IOException;

@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    private final ApiKeyService apiKeyService;
    private final HandlerExceptionResolver resolver;

    public ApiKeyAuthFilter(ApiKeyService apiKeyService,
                            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.apiKeyService = apiKeyService;
        this.resolver = resolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws ServletException, IOException {
        try {
            apiKeyService.validateAndSetSecCtx(req);
            chain.doFilter(req, res);
        } catch (RuntimeException ex) {
            resolver.resolveException(req, res, null, ex);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest req) {
        return apiKeyService.shouldNotFilter(req);
    }

}
