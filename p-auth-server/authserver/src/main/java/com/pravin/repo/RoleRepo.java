package com.pravin.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pravin.entity.RoleEntity;

@Repository
public interface RoleRepo extends CrudRepository<RoleEntity, Long>{
	RoleEntity findByRole(String role);
}
