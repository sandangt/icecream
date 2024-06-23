package sanlab.icecream.frontier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.frontier.model.Category;

import java.util.Optional;
import java.util.UUID;

public interface ICategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> findFirstByOrderByName();

}
