package com.pravin.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pravin.entity.RoleEntity;
import com.pravin.entity.UserEntity;
import com.pravin.model.Role;
import com.pravin.model.User;
import com.pravin.repo.RoleRepo;
import com.pravin.repo.UserRepo;

@Service
public class UserService {

	@Resource
	private UserRepo repo;
	
	@Resource
	private RoleRepo roleRepo;
	
	public void addUser(User user) {
		UserEntity entity = new UserEntity();
		entity.setUsername(user.getUsername());
		entity.setPassword(user.getPassword());
		entity.setRole(roleRepo.findByRole(user.getRole().toString()));
		repo.save(entity);
	}
	
	public void addRole(Role role) {
		RoleEntity entity = new RoleEntity();
		entity.setRole(role.name());
		roleRepo.save(entity);
	}
}
