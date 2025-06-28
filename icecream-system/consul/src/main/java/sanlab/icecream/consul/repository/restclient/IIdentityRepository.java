package sanlab.icecream.consul.repository.restclient;

import sanlab.icecream.consul.dto.core.RegisteredUserInfoDto;

import java.util.Optional;

public interface IIdentityRepository {

    Optional<RegisteredUserInfoDto> getRegisteredUserInfo();

}
