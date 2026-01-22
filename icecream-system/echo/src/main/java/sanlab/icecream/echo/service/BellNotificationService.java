package sanlab.icecream.echo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sanlab.icecream.echo.model.BellNotificationMessage;
import sanlab.icecream.echo.repository.crud.BellNotificationMessageRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BellNotificationService {

    private final BellNotificationMessageRepository notiRepo;

    public List<BellNotificationMessage> getAllByEmail(String email) {
        return notiRepo.findAllByEmail(email);
    }

    public List<BellNotificationMessage> getAllNewByEmail(String email) {
        return notiRepo.findAllByUnSeenAndEmail(email);
    }

}
