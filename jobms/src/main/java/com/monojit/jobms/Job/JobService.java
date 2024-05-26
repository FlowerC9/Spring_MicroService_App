package com.monojit.jobms.Job;

import com.monojit.jobms.Job.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    void createJob(Job job);

    JobDTO getJobById(Long id);

    Boolean removeJob(Long id);

    Boolean updateJob(Long id, Job updatedJob);
}
