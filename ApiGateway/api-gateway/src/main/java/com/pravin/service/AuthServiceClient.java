package com.pravin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("auth-server")
public interface AuthServiceClient {

	@GetMapping("/isexpired/{token}")
	public ResponseEntity<Boolean> isExpired(@PathVariable("token") String token);
}
