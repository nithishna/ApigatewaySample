package com.pravin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pravin.model.BenefitPlan;
import com.pravin.service.BenefitPlanService;

import javassist.NotFoundException;

@RestController
public class BenefitPlanController {

	@Autowired
	BenefitPlanService service;
	
	@PostMapping("/plan")
	public void addBenefitPlan(@RequestBody BenefitPlan plan) {
		service.addBenefitPlan(plan);
	}
	
	@GetMapping("/plan")
	public List<BenefitPlan> getAllPlans(){
		return service.getAllBenefitPlan();
	}
	
	@GetMapping("/plan/{id}")
	public BenefitPlan getPlanById(@PathVariable("id") long id) throws NotFoundException {
		return service.getBenefitPlanById(id);
	}
}
