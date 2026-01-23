package sanlab.icecream.horus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.horus.model.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {}
