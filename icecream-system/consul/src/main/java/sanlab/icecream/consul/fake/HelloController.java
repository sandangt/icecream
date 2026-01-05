package sanlab.icecream.consul.fake;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanlab.icecream.consul.repository.queue.HelloMateQueueRepository;

@RestController
@RequestMapping("/api/hello")
@RequiredArgsConstructor
public class HelloController {

    private final HelloMateQueueRepository helloRepository;

    @PostMapping("/queues/trigger")
    public ResponseEntity<Void> triggerQueues() {
        helloRepository.sayHelloKafka();
        helloRepository.sayHelloRabbit();
        return ResponseEntity.ok().build();
    }

}
