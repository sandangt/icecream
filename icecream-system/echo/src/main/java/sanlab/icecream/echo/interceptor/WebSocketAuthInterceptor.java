package sanlab.icecream.echo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import sanlab.icecream.echo.service.security.JwtAuthConverter;

import java.util.Optional;

@Component
@Slf4j
public class WebSocketAuthInterceptor implements ChannelInterceptor {

    private final JwtAuthConverter jwtConverter;

    public WebSocketAuthInterceptor(JwtAuthConverter jwtConverter) {
        this.jwtConverter = jwtConverter;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        var accessorOptional = Optional.ofNullable(MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class));
        if (accessorOptional.filter(inner -> StompCommand.CONNECT.equals(inner.getCommand())).isEmpty()) {
            return message;
        }
        var accessor = accessorOptional.get();
        Optional<String> tokenOptional = Optional.ofNullable(accessor.getFirstNativeHeader("Authorization"));
        if (tokenOptional.isEmpty()) return message;
        Authentication auth = jwtConverter.convertStr(tokenOptional.get());
        accessor.setUser(auth);
        return message;
    }

}
