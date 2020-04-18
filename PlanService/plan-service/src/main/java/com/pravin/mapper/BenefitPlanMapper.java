package com.pravin.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.pravin.entity.BenefitPlanEntity;
import com.pravin.model.BenefitPlan;


@Mapper
public interface BenefitPlanMapper {

	BenefitPlanMapper mapper = Mappers.getMapper(BenefitPlanMapper.class);
	
	@Named("toEntity")
	BenefitPlanEntity convertToEntity(BenefitPlan dto);
	
	@Named("toDto")
	BenefitPlan convertToDto(BenefitPlanEntity entity);
	
	@IterableMapping(qualifiedByName = "toDto")
	List<BenefitPlan> convertEntitiesToDtos(List<BenefitPlanEntity> entities);	
}
