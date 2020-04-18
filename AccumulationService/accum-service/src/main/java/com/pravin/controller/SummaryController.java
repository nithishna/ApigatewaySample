package com.pravin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pravin.service.SummaryService;

import javassist.NotFoundException;

@RestController
public class SummaryController {

	@Autowired
	private SummaryService service;
	
	@PutMapping("/user/{username}/price/{price}")
	public void updateSummaryPrice(@PathVariable("username") String username, @PathVariable("price") Long price) throws NotFoundException {
		service.updateSummaryPrice(username, price);
	}
	
	@PutMapping("/user/{username}/service/{count}")
	public void updateSpecialServiceUsedCount(@PathVariable("username") String username, @PathVariable("count") Long count) {
		service.updateSpecialServiceUsedCount(username, count);
	}	
}
