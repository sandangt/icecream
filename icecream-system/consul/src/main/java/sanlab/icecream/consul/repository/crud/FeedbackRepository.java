package sanlab.icecream.consul.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.consul.model.Feedback;

import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {

    void deleteById(UUID id);

}
