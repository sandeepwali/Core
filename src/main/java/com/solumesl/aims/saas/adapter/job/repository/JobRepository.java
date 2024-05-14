package com.solumesl.aims.saas.adapter.job.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solumesl.aims.saas.adapter.entity.job.Job;
import com.solumesl.aims.saas.adapter.entity.job.JobIdPK;
import com.solumesl.aims.saas.adapter.entity.job.JobStatus;

public interface JobRepository extends JpaRepository<Job,JobIdPK>{

	List<Job> findByJobStatus(JobStatus jobStatus);
}
