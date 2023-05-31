package sanlab.icecream.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WelcomeController {
    @GetMapping("/")
    public String sayWelcome() {
        return "<h1>Icecream gateway is up and running</h1>";
    }
}
