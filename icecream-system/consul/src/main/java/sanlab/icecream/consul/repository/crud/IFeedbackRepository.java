package sanlab.icecream.consul.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.consul.model.Feedback;

import java.util.UUID;

public interface IFeedbackRepository extends JpaRepository<Feedback, UUID> {

    void deleteById(UUID id);

}
