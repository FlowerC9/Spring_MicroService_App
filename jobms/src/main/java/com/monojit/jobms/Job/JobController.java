package com.monojit.jobms.Job;

import com.monojit.jobms.Job.dto.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createJobs(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully",HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id){
        JobDTO jobDTO = jobService.getJobById(id);
        if(jobDTO!=null) {
            return new ResponseEntity<>(jobDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>removeJob(@PathVariable Long id){
        Boolean removed=jobService.removeJob(id);
        if(removed==true)
        return new ResponseEntity<>("job removed",HttpStatus.OK);
        else return new ResponseEntity<>("job not found",HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String>updateJob(@PathVariable Long id,@RequestBody Job updatedJob){
        Boolean updated=jobService.updateJob(id,updatedJob);
        if(updated==true)
        return new ResponseEntity<>("job updated",HttpStatus.OK);
        else return new ResponseEntity<>("job not found",HttpStatus.NOT_FOUND);
    }
}
