package sanlab.icecream.horus;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Theme("hilla")
@SpringBootApplication
public class Application implements AppShellConfigurator {
    static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
