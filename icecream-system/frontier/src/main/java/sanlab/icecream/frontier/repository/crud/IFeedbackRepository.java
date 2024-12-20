package sanlab.icecream.frontier.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.frontier.model.Feedback;

import java.util.Optional;
import java.util.UUID;

public interface IFeedbackRepository extends JpaRepository<Feedback, UUID> {

    void deleteById(UUID id);

}
