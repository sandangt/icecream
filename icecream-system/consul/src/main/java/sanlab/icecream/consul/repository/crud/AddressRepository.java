package sanlab.icecream.consul.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.consul.model.Address;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    List<Address> findAllByOrderByCreatedAt();
    Optional<Address> findFirstById(UUID id);
}
