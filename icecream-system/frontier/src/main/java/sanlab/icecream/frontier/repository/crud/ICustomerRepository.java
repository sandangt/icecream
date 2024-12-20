package sanlab.icecream.frontier.repository.crud;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.frontier.model.Customer;

import java.util.Optional;
import java.util.UUID;

public interface ICustomerRepository extends JpaRepository<Customer, UUID> {
    @NotNull
    Page<Customer> findAll(@NotNull Pageable pageable);
    Optional<Customer> findFirstByUserId(UUID id);
}
