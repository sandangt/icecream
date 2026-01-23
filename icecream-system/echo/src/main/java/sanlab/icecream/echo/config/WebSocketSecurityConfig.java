package sanlab.icecream.echo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import sanlab.icecream.echo.interceptor.WebSocketAuthInterceptor;

@Configuration
public class WebSocketSecurityConfig implements WebSocketMessageBrokerConfigurer {

    private final WebSocketAuthInterceptor wsInterceptor;

    public WebSocketSecurityConfig(
        WebSocketAuthInterceptor wsInterceptor) {
        this.wsInterceptor = wsInterceptor;
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(wsInterceptor);
    }

}
