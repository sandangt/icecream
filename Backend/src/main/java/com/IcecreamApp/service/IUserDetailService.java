package com.IcecreamApp.service;

import java.util.List;
import java.util.Optional;

import com.IcecreamApp.DTO.UserDetailDTO;
import com.IcecreamApp.entity.UserDetail;

public interface IUserDetailService {

	List<UserDetailDTO> readAll();

	Optional<UserDetailDTO> readById(long id);

	UserDetail create(UserDetailDTO userDetailDTO);

	Optional<UserDetail> update(long id, UserDetailDTO userDetailDTO);

	boolean delete(long id);

}
