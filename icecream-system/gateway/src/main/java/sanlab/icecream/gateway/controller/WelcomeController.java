package sanlab.icecream.gateway.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WelcomeController {
    @GetMapping("/hello")
    @PreAuthorize("hasRole('icecream-client-normie')")
    public String sayWelcome() {
        return "<h1>Icecream gateway is up and running</h1>";
    }
}
