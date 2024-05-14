package com.solumesl.aims.saas.adapter.job.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solumesl.aims.saas.adapter.entity.job.JobSummary;
import com.solumesl.aims.saas.adapter.entity.job.JobSummaryPK;

public interface JobSummaryRepository extends JpaRepository<JobSummary,JobSummaryPK>{

	public List<JobSummary> findByJobId(long jobId);
	
	public List<JobSummary> findByParentJobId(long jobId);
}
