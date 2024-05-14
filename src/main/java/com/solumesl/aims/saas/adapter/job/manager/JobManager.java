package com.solumesl.aims.saas.adapter.job.manager;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.solumesl.aims.saas.adapter.entity.job.Job;
import com.solumesl.aims.saas.adapter.entity.job.JobStatus;
import com.solumesl.aims.saas.adapter.entity.job.JobSummary;
import com.solumesl.aims.saas.adapter.model.ClientResponse;

public interface JobManager {

	static long generateJobId() {
		return Instant.now().toEpochMilli() + Math.abs(UUID.randomUUID().getMostSignificantBits());
	}


	public Job writeJobStatus(long jobId, JobStatus jobStatus);

	public Job writeJobStatus(long parentJobId, long jobId, JobStatus jobStatus);

	public Job writeJobStatus(long jobId, JobStatus jobStatus, ClientResponse result);

	public Job writeJobStatus(long parentJobId, long jobId, JobStatus jobStatus, ClientResponse result);

	public  void writeJobSummary(long parentJobId, long jobId, Object join);

	public Object readJobStatus(long jobId);

	Object readJobSummary(long jobId);

	List<JobSummary> readJobSummaryByParent(long jobId);

	Optional<List<Job>> getAllActiveMasterJob(JobStatus jobStatus);

	Optional<List<Job>> findAll(List<Long> childJobs);


	
	Job updateChild(long jobId, Set<Long> arr);
}
