package sanlab.icecream.lookup.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sanlab.icecream.lookup.model.Media;


@Repository
public interface IMediaRepository extends JpaRepository<Media, UUID> {
    Page<Media> findAllByOrderByCreatedOnDesc(Pageable pageable);
    Page<Media> findAllByOrderByLastModifiedOnDesc(Pageable pageable);
}
