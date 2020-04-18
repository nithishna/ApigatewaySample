package com.pravin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pravin.entity.BenefitPlanAssociationEntity;
import com.pravin.entity.BenefitPlanEntity;
import com.pravin.mapper.BenefitPlanAssociationMapper;
import com.pravin.mapper.BenefitPlanMapper;
import com.pravin.model.BenefitPlan;
import com.pravin.model.BenefitPlanAssociation;
import com.pravin.repo.BenefitPlanAssociationRepo;
import com.pravin.repo.BenefitPlanRepo;

import javassist.NotFoundException;

@Service
public class BenefitPlanService {

	@Resource
	BenefitPlanRepo repo;
	
	@Resource
	BenefitPlanAssociationRepo associationRepo;
	
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
	
	public BenefitPlanAssociation getAllPlansByUsername(String username){
		BenefitPlanAssociationEntity entity = associationRepo.findByUsername(username);
		BenefitPlan planDto = BenefitPlanMapper.mapper.convertToDto(entity.getBenefitPlan());
		BenefitPlanAssociation dto = BenefitPlanAssociationMapper.mapper.convertToDto(entity);
		dto.setBenefitPlan(planDto);
		return dto;
	}
	
	public void addBenefitPlanAssociation(BenefitPlanAssociation dto) throws NotFoundException {
		Optional<BenefitPlanEntity> opEntity = repo.findById(dto.getBenefitPlan().getId());
		if(!opEntity.isPresent())
			throw new NotFoundException("No such plan");
		BenefitPlanEntity entity = opEntity.get();
		BenefitPlanAssociationEntity association = associationRepo.findByUsername(dto.getUsername());
		if(association != null)
			throw new IllegalStateException("There is already a plan associated with the user");
		association = new BenefitPlanAssociationEntity();
		association.setUsername(dto.getUsername());
		association.setEndDate(dto.getEndDate());
		association.setStartDate(dto.getStartDate());
		association.setBenefitPlan(entity);
		associationRepo.save(association);
	}
}
