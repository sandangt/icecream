package sanlab.icecream.consul.service.security;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static sanlab.icecream.fundamentum.constant.ERole.ANON;
import static sanlab.icecream.fundamentum.constant.ERole.NORMIE;

public class UrlAuthorizer {

    private UrlAuthorizer() {}

    private static final String ALL_METHODS = "get post put patch delete";
    private static final String ALL_NON_DELETE_METHODS = "get post put patch";

    private static final List<UrlMarcher> urlMarchers = List.of(
        new UrlMarcher(ANON, "get", "/categories"),
        new UrlMarcher(ANON, "get", "/products"),
        new UrlMarcher(NORMIE, ALL_NON_DELETE_METHODS, "/categories"),
        new UrlMarcher(NORMIE, ALL_NON_DELETE_METHODS, "/categories/**"),
        new UrlMarcher(NORMIE, ALL_NON_DELETE_METHODS, "/products"),
        new UrlMarcher(NORMIE, ALL_NON_DELETE_METHODS, "/products/**")
    );

    public static UrlMarcher findUrl(HttpServletRequest request) {
        return urlMarchers.stream().
            filter(item -> item.match(request))
            .findFirst().orElse(null);
    }

}
