package com.pravin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pravin.entity.BenefitPlanEntity;
import com.pravin.mapper.BenefitPlanMapper;
import com.pravin.model.BenefitPlan;
import com.pravin.repo.BenefitPlanRepo;

import javassist.NotFoundException;

@Service
public class BenefitPlanService {

	@Resource
	BenefitPlanRepo repo;
	
	public void addBenefitPlan(BenefitPlan dto) {
		BenefitPlanEntity entity = BenefitPlanMapper.mapper.convertToEntity(dto);
		repo.save(entity);
	}
	
	public List<BenefitPlan> getAllBenefitPlan() {
		Iterable<BenefitPlanEntity> iterableEntities = repo.findAll();
		List<BenefitPlanEntity> entities = new ArrayList<>();
		iterableEntities.forEach(entities::add);
		return BenefitPlanMapper.mapper.convertEntitiesToDtos(entities);
	}
	
	public BenefitPlan getBenefitPlanById(long id) throws NotFoundException {
		Optional<BenefitPlanEntity> entity = repo.findById(id);
		if(entity.isPresent()) {
			return BenefitPlanMapper.mapper.convertToDto(entity.get());
		}else
			throw new NotFoundException("Plan not found");
	}
}
