package com.solumesl.aims.saas.adapter.entity.job.audit;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
@MappedSuperclass
public abstract class  Auditable {
	@Column(name = "created_date", nullable = false, updatable = false)
	@CreatedDate
	private Date createdDate;

	@Column(name = "modified_date")
	@LastModifiedDate
	private Date modifiedDate;
}
