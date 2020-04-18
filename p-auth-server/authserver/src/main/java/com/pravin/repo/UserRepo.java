package com.pravin.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pravin.entity.UserEntity;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, String>{
	UserEntity findByUsernameAndPassword(String username, String password);
}