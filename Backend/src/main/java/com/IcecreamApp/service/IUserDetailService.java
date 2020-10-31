package com.IcecreamApp.service;

import java.util.List;

import com.IcecreamApp.DTO.UserDetailDTO;

public interface IUserDetailService {

	UserDetailDTO create(UserDetailDTO userDetailDTO);

	List<UserDetailDTO> readAll();

	UserDetailDTO readById(long id);

	UserDetailDTO update(long id, UserDetailDTO userDetailDTO);

	void delete(long id);

}
