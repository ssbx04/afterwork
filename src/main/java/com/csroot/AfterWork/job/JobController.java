package com.csroot.AfterWork.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    ResponseEntity<List<Job>> findAll(){
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<String> addJob(@RequestBody Job job){
        jobService.addJob(job);
        return new ResponseEntity<>("Job successfully added",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<Job> getJob(@PathVariable Long id){
        Job job = jobService.getJobById(id);
        if(job != null)
            return new ResponseEntity<>(job, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleteState = jobService.deleteJobById(id);
        if (deleteState)
            return new ResponseEntity<>("Job successfully deleted",HttpStatus.OK);
        else
            return new ResponseEntity<>("Unable to delete job " + id,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updateState = jobService.updateJobById(id,updatedJob);
        if (updateState)
            return new ResponseEntity<>("Job successfully updated",HttpStatus.OK);
        else
            return new ResponseEntity<>("Unable to update job " + id,HttpStatus.NOT_FOUND);
    }
}
