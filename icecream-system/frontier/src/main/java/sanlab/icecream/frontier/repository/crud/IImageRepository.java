package sanlab.icecream.frontier.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.frontier.model.Image;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IImageRepository extends JpaRepository<Image, UUID> {
    List<Image> findAllByOrderById();
    Optional<Image> findFirstByRelativePath(String relativePath);
}
