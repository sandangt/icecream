package sanlab.icecream.consul.repository.queue;

import java.util.function.Supplier;
import org.springframework.messaging.Message;

public interface HelloMateQueueRepository {
//    Supplier<Message<String>> sayHelloKafka();
//    Supplier<Message<String>> sayHelloRabbit();

    void sayHelloKafka();
    void sayHelloRabbit();

}
