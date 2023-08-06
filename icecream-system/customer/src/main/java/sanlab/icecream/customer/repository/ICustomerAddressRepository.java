package sanlab.icecream.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sanlab.icecream.customer.model.CustomerAddress;


@Repository
public interface ICustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {}
