package sanlab.icecream.frontier.service.security;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import sanlab.icecream.fundamentum.constant.ERole;

import java.util.function.Supplier;

@Component
public class StorefrontAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Override
    public AuthorizationDecision check(Supplier<Authentication> reqAuthentication, RequestAuthorizationContext ctx) {
        var auth = reqAuthentication.get();
        UrlMarcher urlMarcher = UrlAuthorizer.findUrl(ctx.getRequest());
        if (urlMarcher == null) {
            return new AuthorizationDecision(false);
        }
        return new AuthorizationDecision(isPermitted(auth, urlMarcher));
    }

    private boolean isPermitted(Authentication auth, UrlMarcher urlMarcher) {
        ERole role = urlMarcher.role();
        return auth.getAuthorities().stream()
            .anyMatch(item -> item.getAuthority().equals(role.getRaw()));
    }

}
