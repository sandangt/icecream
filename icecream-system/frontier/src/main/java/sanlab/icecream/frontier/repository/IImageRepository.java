package sanlab.icecream.frontier.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.frontier.model.Image;

import java.util.List;
import java.util.UUID;

public interface IImageRepository extends JpaRepository<Image, UUID> {
//    Page<Image> findAllByOrderById(Pageable pageable);
    List<Image> findAllByOrderById();
}
