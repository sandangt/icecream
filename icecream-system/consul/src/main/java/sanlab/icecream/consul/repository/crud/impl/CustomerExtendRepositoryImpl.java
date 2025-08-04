package sanlab.icecream.consul.repository.crud.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import sanlab.icecream.consul.dto.core.CustomerDto;
import sanlab.icecream.consul.model.Address;
import sanlab.icecream.consul.model.Customer;
import sanlab.icecream.consul.repository.crud.CustomerExtendRepository;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.ADDRESS_NOT_FOUND;

public class CustomerExtendRepositoryImpl implements CustomerExtendRepository {

    private final EntityManager entityManager;

    public CustomerExtendRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void updateCustomerInfo(UUID id, CustomerDto customerDto) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Customer> criteriaUpdate = cb.createCriteriaUpdate(Customer.class);
        Root<Customer> root = criteriaUpdate.from(Customer.class);
        if (StringUtils.isNotBlank(customerDto.getPhone())) {
            criteriaUpdate.set(root.get("phone"), customerDto.getPhone());
        }
        if (StringUtils.isNotBlank(customerDto.getLastName())) {
            criteriaUpdate.set(root.get("lastName"), customerDto.getLastName());
        }
        if (StringUtils.isNotBlank(customerDto.getFirstName())) {
            criteriaUpdate.set(root.get("firstName"), customerDto.getFirstName());
        }
        criteriaUpdate
            .where(cb.equal(root.get("userId"), id));
        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }

    @Override
    public void setPrimaryAddress(UUID id, UUID addressId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Address address = entityManager.getReference(Address.class, addressId);
        if (address == null) throw new IcRuntimeException(ADDRESS_NOT_FOUND);
        CriteriaUpdate<Customer> criteriaUpdate = cb.createCriteriaUpdate(Customer.class);
        Root<Customer> root = criteriaUpdate.from(Customer.class);
        criteriaUpdate
            .set(root.get("primaryAddress"), address)
            .where(cb.equal(root.get("userId"), id));
        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }


}
