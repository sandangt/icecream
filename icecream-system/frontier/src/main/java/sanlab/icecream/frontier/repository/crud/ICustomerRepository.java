package sanlab.icecream.frontier.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.frontier.model.Customer;

import java.util.UUID;

public interface ICustomerRepository extends JpaRepository<Customer, UUID> {}
