package com.pravin.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pravin.config.JwtTokenUtil;
import com.pravin.entity.UserEntity;
import com.pravin.model.User;
import com.pravin.repo.UserRepo;

import javassist.NotFoundException;

@Service
public class JwtUserService {

	@Resource
	private UserRepo repo;
	
	@Autowired
	private JwtTokenUtil tokenGenerator;
	
	public String generateToken(User user) throws NotFoundException {
		UserEntity entity = repo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if(entity == null)
			throw new NotFoundException("No such user");
		return tokenGenerator.generateToken(user);
	}
	
	
	public boolean isExpired(String token) {
		return tokenGenerator.isTokenExpired(token);
	}
}
