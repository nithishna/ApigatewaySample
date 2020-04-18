package com.pravin.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pravin.entity.BenefitPlanEntity;

@Repository
public interface BenefitPlanRepo extends CrudRepository<BenefitPlanEntity, Long> {

}
