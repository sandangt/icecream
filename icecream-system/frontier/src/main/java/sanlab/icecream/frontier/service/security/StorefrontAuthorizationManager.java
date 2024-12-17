package sanlab.icecream.frontier.service.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class StorefrontAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Override
    public AuthorizationDecision check(Supplier<Authentication> reqAuthentication, RequestAuthorizationContext ctx) {
        var auth = reqAuthentication.get();
        if (auth instanceof AnonymousAuthenticationToken) {
            return new AuthorizationDecision(true);
        }
        var principal = auth.getPrincipal();
        return new AuthorizationDecision(true);
    }
}
