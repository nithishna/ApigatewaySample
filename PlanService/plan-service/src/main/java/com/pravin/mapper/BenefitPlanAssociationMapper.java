package com.pravin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.pravin.entity.BenefitPlanAssociationEntity;
import com.pravin.model.BenefitPlanAssociation;

@Mapper
public interface BenefitPlanAssociationMapper {
	BenefitPlanAssociationMapper mapper = Mappers.getMapper(BenefitPlanAssociationMapper.class);
	
	@Mappings({
		@Mapping(target = "benefitPlan" , ignore = true)
	})
	BenefitPlanAssociationEntity convertToEntity(BenefitPlanAssociation dto);
	
	
	@Mappings({
		@Mapping(target = "benefitPlan" , ignore = true)
	})
	BenefitPlanAssociation convertToDto(BenefitPlanAssociationEntity entity);
}
