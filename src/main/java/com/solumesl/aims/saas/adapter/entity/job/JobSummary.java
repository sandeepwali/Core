package com.solumesl.aims.saas.adapter.entity.job;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.solumesl.aims.saas.adapter.entity.job.audit.Auditable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "jobsummary")
@Data
@EqualsAndHashCode(callSuper=false)
@IdClass(JobSummaryPK.class)
@EntityListeners(AuditingEntityListener.class)
public class JobSummary extends Auditable{

	@Id
	@Column(name = "jobid")
	private long jobId;

	@Id
	@Column(name = "parentjobid")
	private long parentJobId;
	@Id
	@Column(name = "custombatchid")
	private String customBatchId;
	
	@Column(name = "response_message")
	private String responseMessage;
	
	
	@Column(name = "response_code")
	private Integer responseCode;
	
	@Column(name = "valid_count")
	private Integer validCount;
	
	@Column(name = "invalid_count")
	private Integer invalidCount;
	
	public JobSummary() {
		super();
	}
}
