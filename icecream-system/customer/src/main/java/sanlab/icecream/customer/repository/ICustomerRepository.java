package sanlab.icecream.customer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sanlab.icecream.customer.model.Customer;


@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    Page<Customer> findAllByOrderByCreatedOnDesc(Pageable pageable);
    Page<Customer> findAllByOrderByLastModifiedOnDesc(Pageable pageable);
}
