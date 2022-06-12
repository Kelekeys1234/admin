package com.admin.admin.vo;

import java.time.LocalDateTime;

public class Facility {
public Long FacilityId;
	
	public String FacilityName;
	
	public String  Description ;
	
	public LocalDateTime CreatedOn;
	
	public String CreatedBy;

	public Long getFacilityId() {
		return FacilityId;
	}

	public void setFacilityId(Long facilityId) {
		FacilityId = facilityId;
	}

	public String getFacilityName() {
		return FacilityName;
	}

	public void setFacilityName(String facilityName) {
		FacilityName = facilityName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public LocalDateTime getCreatedOn() {
		return CreatedOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		CreatedOn = createdOn;
	}

	public String getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	public Facility(String facilityName, String description, LocalDateTime createdOn, String createdBy) {
		super();
		FacilityName = facilityName;
		Description = description;
		CreatedOn = createdOn;
		CreatedBy = createdBy;
	}

	public Facility() {
		super();
	}
	

}
