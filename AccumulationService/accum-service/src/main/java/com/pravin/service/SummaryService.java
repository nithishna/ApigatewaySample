package com.pravin.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pravin.entity.Summary;
import com.pravin.model.BenefitPlan;
import com.pravin.model.BenefitPlanAssociation;
import com.pravin.repo.SummaryRepo;

import javassist.NotFoundException;

@Service
public class SummaryService {

	@Resource
	private  SummaryRepo repo;
	
	@Autowired
	private PlanServiceClient client;
	
	public void updateSummaryPrice(String username, Long price) throws NotFoundException {
		BenefitPlanAssociation association = client.getPlansByUsername(username);
		BenefitPlan plan = association.getBenefitPlan();
		if(plan == null)
			throw new IllegalStateException("No plan for user");
		Summary summary = repo.findByUsername(username);
		if(summary == null) {
			summary = new Summary();
			summary.setUsername(username);
			summary.setSpecialServiceUsedCount(0l);
			summary.setCoinsuranceTotal(0);
			summary.setDeductibleTotal(plan.getDeductible());
			summary.setInsuranceCompanyTotal(0);
		}
		if(plan.getCoInsurance() > 0) {
			if(plan.getDeductible() > 0) {
				summary.setDeductibleTotal(summary.getDeductibleTotal() - price);
			}else {
				summary.setCoinsuranceTotal(summary.getCoinsuranceTotal() + price * (plan.getCoInsurance()/100));
				summary.setInsuranceCompanyTotal(summary.getInsuranceCompanyTotal() + price * (1 - (plan.getCoInsurance()/100)));
			}
		}else if(plan.getCopay() > 0) {
			summary.setInsuranceCompanyTotal(summary.getInsuranceCompanyTotal() + (price - plan.getCopay()));
		}
		repo.save(summary);
	}
	
	public void updateSpecialServiceUsedCount(String username, Long count) {
		BenefitPlanAssociation association = client.getPlansByUsername(username);
		BenefitPlan plan = association.getBenefitPlan();
		if(plan == null)
			throw new IllegalStateException("No plan for user");
		Summary summary = repo.findByUsername(username);
		if(summary == null) {
			summary = new Summary();
			summary.setUsername(username);
			summary.setSpecialServiceUsedCount(0l);
			summary.setCoinsuranceTotal(0);
			summary.setDeductibleTotal(plan.getDeductible());
			summary.setInsuranceCompanyTotal(0);
		}
		summary.setSpecialServiceUsedCount(summary.getSpecialServiceUsedCount() + count);
		repo.save(summary);
	}
}
