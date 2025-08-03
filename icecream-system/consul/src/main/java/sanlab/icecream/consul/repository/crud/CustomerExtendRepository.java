package sanlab.icecream.consul.repository.crud;

import sanlab.icecream.consul.dto.core.CustomerDto;

import java.util.UUID;

public interface CustomerExtendRepository {

    void updateCustomerInfo(CustomerDto customer);
    void setPrimaryAddress(UUID id, UUID addressId);

}
