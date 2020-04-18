package com.pravin.model;

import java.io.Serializable;
import java.util.Date;

public class BenefitPlanAssociation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String username;
	
	private BenefitPlan benefitPlan;
	
	private Date startDate;
	private Date  endDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public BenefitPlan getBenefitPlan() {
		return benefitPlan;
	}
	public void setBenefitPlan(BenefitPlan benefitPlan) {
		this.benefitPlan = benefitPlan;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
