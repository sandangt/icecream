package sanlab.icecream.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sanlab.icecream.product.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByOrderByCreatedOnDesc(Pageable pageable);
    Page<Product> findAllByOrderByLastModifiedOnDesc(Pageable pageable);
}
