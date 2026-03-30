package sanlab.icecream.consul.fake;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanlab.icecream.fundamentum.utils.LogUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static sanlab.icecream.fundamentum.constant.EPreAuthorizeRole.HAS_ROLE_GARDENER;

@Slf4j
@RestController
@RequestMapping("/api/fake")
@PreAuthorize(HAS_ROLE_GARDENER)
public class FakerController {

    private final FakerService fakerService;
    private final Executor vThreadExecutor;

    public FakerController(FakerService fakerService) {
        this.fakerService = fakerService;
        this.vThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();
    }

    @PostMapping("/seed")
    public ResponseEntity<Void> seedData() {
        CompletableFuture.runAsync(fakerService::seed, vThreadExecutor)
                .exceptionally(ex -> {
                    LogUtils.logThrowable(log, ex);
                    return null;
                });
        return ResponseEntity.ok().build();
    }

}
