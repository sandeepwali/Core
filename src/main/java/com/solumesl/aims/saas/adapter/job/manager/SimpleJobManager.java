package com.solumesl.aims.saas.adapter.job.manager;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.solumesl.aims.saas.adapter.entity.job.Job;
import com.solumesl.aims.saas.adapter.entity.job.JobIdPK;
import com.solumesl.aims.saas.adapter.entity.job.JobStatus;
import com.solumesl.aims.saas.adapter.entity.job.JobSummary;
import com.solumesl.aims.saas.adapter.job.repository.JobRepository;
import com.solumesl.aims.saas.adapter.job.repository.JobSummaryRepository;
import com.solumesl.aims.saas.adapter.model.ClientResponse;
import com.solumesl.aims.saas.adapter.util.SolumSaasUtil;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class SimpleJobManager implements JobManager {

	private JobRepository jobRepository;
	public SimpleJobManager(JobRepository jobRepository, JobSummaryRepository jobSummaryRepository) {
		super();
		this.jobRepository = jobRepository;
		this.jobSummaryRepository = jobSummaryRepository;
	}
	private JobSummaryRepository jobSummaryRepository;

	@Override
	public Job writeJobStatus(long jobId, JobStatus jobStatus) {
		Job job = createJob(jobId, jobStatus);
		return saveJob(jobId, job);

	}

	private Job createJob(long jobId, JobStatus jobStatus) {
		Optional<Job> data = readJobStatus(jobId);
		Job job = null;
		if(data.isPresent()) {
			job = data.get();
			job.setJobStatus(jobStatus);
		}else {
			job = Job.builder().jobId(jobId).jobStatus(jobStatus).build();
		}
		return job;
	}

	private Job saveJob(long jobId, Job job) {
		job = jobRepository.save(job);
		log.info("Job Tracker,Job ID {} , Status {}",jobId, job.getJobStatus());
		return job;
	}
	private Job saveJob(long parentJobId, long jobId, Job job) {
		job = jobRepository.save(job);
		log.info("Job Tracker Parent {},Job ID {} , Status {}",parentJobId, jobId, job.getJobStatus());
		return job;
	}
	@Override
	public Job writeJobStatus(long jobId, JobStatus jobStatus, ClientResponse result) {
		Job job = createJob(jobId, jobStatus);
		job.setResult(result);
		return saveJob(jobId, job);
	}

	@Override
	public Job updateChild(long jobId, Set<Long> childJobs) {
		Job data = readJobStatus(jobId).get();
		data.setChildJobs(Lists.newArrayList(childJobs));
		return jobRepository.save(data);
	}

	@Override
	public Optional<Job> readJobStatus(long jobId) {
		JobIdPK jobIdPK = new JobIdPK();
		jobIdPK.setJobId(jobId);
		return jobRepository.findById(jobIdPK);
	}

	@Override
	public void writeJobSummary(long parentJobId, long jobId, Object obj) {
		JobSummary jobSummary = new JobSummary();
		jobSummary.setJobId(jobId);
		jobSummary.setParentJobId(parentJobId);
		@SuppressWarnings("unchecked")
		Map<String, Object> map = SolumSaasUtil.getoMapper().convertValue(obj, Map.class);
		jobSummary.setCustomBatchId((String) map.get("customBatchId"));
		jobSummary.setResponseMessage((String) map.get("responseMessage"));
		jobSummary.setInvalidCount((Integer) map.get("invalidCount"));
		jobSummary.setValidCount((Integer) map.get("validCount"));
		log.info("Job Tracker Parent {},Job ID {} , BatchID {},responseMessage {} ",parentJobId, jobId, map.get("customBatchId"), map.get("responseMessage"));
		jobSummaryRepository.save(jobSummary);
	}

	@Override
	public Object readJobSummary(long jobId) {
		return jobSummaryRepository.findByJobId(jobId);
	}

	@Override
	public Job writeJobStatus(long parentJobId, long jobId, JobStatus jobStatus) {
		Job job = createJob(jobId, jobStatus);
		return saveJob(parentJobId, jobId, job);
	}

	@Override
	public Job writeJobStatus(long parentJobId, long jobId, JobStatus jobStatus, ClientResponse result) {
		Job job = createJob(jobId, jobStatus);
		job.setResult(result);
		return saveJob(parentJobId,jobId, job);
	}
	@Override
	public Optional<List<Job>> getAllActiveMasterJob(JobStatus jobStatus){
		return  Optional.ofNullable(jobRepository.findByJobStatus(jobStatus));
	}

	@Override
	public Optional<List<Job>> findAll(List<Long> childJobs) {
		if(SolumSaasUtil.isEmpty(childJobs)) {
			return Optional.empty();
		}
		List<JobIdPK> ids = childJobs.stream().map(id-> {JobIdPK jobId = new JobIdPK() ;jobId.setJobId(id); return jobId; }).collect(Collectors.toList());
		return Optional.ofNullable(jobRepository.findAllById(ids));
	}
	@Override
	public List<JobSummary> readJobSummaryByParent(long jobId) {
		return jobSummaryRepository.findByParentJobId(jobId);
	}


}
