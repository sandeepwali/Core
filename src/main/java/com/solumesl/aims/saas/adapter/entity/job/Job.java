package com.solumesl.aims.saas.adapter.entity.job;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonView;
import com.solumesl.aims.saas.adapter.entity.job.audit.Auditable;
import com.solumesl.aims.saas.adapter.entity.job.view.View;
import com.solumesl.aims.saas.adapter.model.ClientResponse;
import com.vladmihalcea.hibernate.type.array.ListArrayType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "jobtracker")
@Data
@EqualsAndHashCode(callSuper=false)
@IdClass(JobIdPK.class)
@EntityListeners(AuditingEntityListener.class)
@TypeDef(
	    name = "list-array",
	    typeClass = ListArrayType.class
	)
@DynamicUpdate
@Builder
@AllArgsConstructor
public class Job extends Auditable{
	@JsonView(View.Admin.class)
	@Id
	@Column(name = "jobid")
	private long jobId;
	@JsonView(View.SuperAdmin.class)
	@Type(type = "list-array")
	@Column(name = "childjobs", columnDefinition="bigint[]")
	private List<Long> childJobs;
	@JsonView(View.Admin.class)
	@Column(name = "jobstatus")
	@Enumerated(EnumType.STRING)
	private JobStatus jobStatus;
	@JsonView(View.Admin.class)
	private ClientResponse result;
	public Job() {
		super();
	}
	
}
