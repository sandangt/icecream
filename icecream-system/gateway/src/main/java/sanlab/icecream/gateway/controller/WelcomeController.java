package sanlab.icecream.gateway.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WelcomeController {
    @GetMapping("/hello")
    @PreAuthorize("hasAnyRole('icecream-client-normie','icecream-client-vip')")
    public String sayWelcome() {
        return "<h1>Icecream gateway is up and running</h1>";
    }

    @GetMapping("/vip-hello")
    @PreAuthorize("hasRole('icecream-client-vip')")
    public String sayWelcomeVip() {
        return """
            <div>
                <h1>Icecream gateway is up and running</h1>
                <p>Hello Mr. Important</p>
            </div>
        """;
    }
}
