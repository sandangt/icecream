package sanlab.icecream.echo.repository.crud;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import sanlab.icecream.echo.model.BellNotificationMessage;

import java.util.List;
import java.util.UUID;

public interface BellNotificationMessageRepository extends MongoRepository<BellNotificationMessage, ObjectId> {

    List<BellNotificationMessage> findAllByEmail(String username);
    List<BellNotificationMessage> findAllByUserId(UUID userId);
    @Query("{'seen': false, 'email': ?0}")
    List<BellNotificationMessage> findAllByUnSeenAndEmail(String email);

}
