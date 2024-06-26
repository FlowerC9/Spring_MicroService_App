package com.monojit.jobms.Job.impl;

import com.monojit.jobms.Job.Job;
import com.monojit.jobms.Job.JobRepository;
import com.monojit.jobms.Job.JobService;
import com.monojit.jobms.Job.cliients.CompanyClient;
import com.monojit.jobms.Job.cliients.ReviewClient;
import com.monojit.jobms.Job.dto.JobDTO;
import com.monojit.jobms.Job.external.Company;
import com.monojit.jobms.Job.external.Review;
import com.monojit.jobms.Job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final RestTemplate restTemplate;
    private CompanyClient companyClient;
    private ReviewClient reviewClient;
    @Autowired
    public JobServiceImpl(JobRepository jobRepository, RestTemplate restTemplate,CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.restTemplate = restTemplate;
        this.companyClient=companyClient;
        this.reviewClient=reviewClient;
    }

    private JobDTO convertToDto(Job job) {
        Company company = null;
        List<Review> reviews = Collections.emptyList();
        try {
            company = companyClient.getCompany(job.getCompanyId());
            reviews= reviewClient.getReviews(job.getCompanyId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JobMapper.mapToJobDto(job, company, reviews);
    }

    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        if (job == null) {
            return null;
        }
        return convertToDto(job);
    }

    @Override
    public Boolean removeJob(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> existingJobOpt = jobRepository.findById(id);
        if (existingJobOpt.isPresent()) {
            Job existingJob = existingJobOpt.get();
            existingJob.setTitle(updatedJob.getTitle());
            existingJob.setDescription(updatedJob.getDescription());
            existingJob.setMinSalary(updatedJob.getMinSalary());
            existingJob.setMaxSalary(updatedJob.getMaxSalary());
            existingJob.setLocation(updatedJob.getLocation());
            jobRepository.save(existingJob);
            return true;
        } else {
            return false;
        }
    }
}
