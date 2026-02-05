package sanlab.icecream.consul.repository.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sanlab.icecream.consul.model.Feedback;

import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {

    Page<Feedback> findAllByProduct_Id(UUID productId, Pageable pageable);

    void deleteById(UUID id);

    Long countByProduct_Id(UUID productId);

    @Query(value = "SELECT AVG(star) FROM FEEDBACK WHERE product_id = :productId", nativeQuery = true)
    Double averageStarByProduct_Id(@Param("productId") UUID productId);

}
