package com.monojit.jobms.Job.mapper;

import com.monojit.jobms.Job.Job;
import com.monojit.jobms.Job.dto.JobDTO;
import com.monojit.jobms.Job.external.Company;
import com.monojit.jobms.Job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobDto(Job job, Company company, List<Review> reviews) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setTitle(job.getTitle());
        jobDTO.setId(job.getId());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);
        return jobDTO;
    }
}
