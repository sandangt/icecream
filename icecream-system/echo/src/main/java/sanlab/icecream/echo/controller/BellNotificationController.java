package sanlab.icecream.echo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanlab.icecream.echo.model.BellNotificationMessage;
import sanlab.icecream.echo.service.BellNotificationService;
import sanlab.icecream.echo.utils.SecurityContextUtils;

import java.util.List;

import static sanlab.icecream.fundamentum.constant.EPreAuthorizeRole.HAS_ROLE_NORMIE;

@RestController
@RequestMapping("/bell-notifications")
@RequiredArgsConstructor
public class BellNotificationController {

    private final BellNotificationService bellNotiService;

    @GetMapping
    @PreAuthorize(HAS_ROLE_NORMIE)
    public ResponseEntity<List<BellNotificationMessage>> getAll() {
        var userDetails = SecurityContextUtils.getRegisteredUserInfo();
        var result = bellNotiService.getAllByEmail(userDetails.getUsername());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/new")
    @PreAuthorize(HAS_ROLE_NORMIE)
    public ResponseEntity<List<BellNotificationMessage>> getNewMessages() {
        var userDetails = SecurityContextUtils.getRegisteredUserInfo();
        var result = bellNotiService.getAllNewByEmail(userDetails.getEmail());
        return ResponseEntity.ok(result);
    }

}
