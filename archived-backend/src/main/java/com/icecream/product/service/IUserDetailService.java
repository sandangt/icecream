package com.icecream.product.service;

import java.util.List;
import java.util.Optional;

import com.icecream.product.DTO.UserDetailDTO;
import com.icecream.product.entity.UserDetail;

public interface IUserDetailService {

	List<UserDetailDTO> readAll();

	Optional<UserDetailDTO> readById(long id);

	Optional<UserDetail> create(UserDetailDTO userDetailDTO);

	Optional<UserDetail> update(long id, UserDetailDTO userDetailDTO);

	boolean delete(long id);

}
