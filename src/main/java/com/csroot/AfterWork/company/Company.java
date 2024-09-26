package com.csroot.AfterWork.company;

import com.csroot.AfterWork.job.Job;
import com.csroot.AfterWork.review.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Job> allJobs;
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Review> allReviews;

    public List<Review> getAllReviews() {
        return allReviews;
    }

    public void setAllReviews(List<Review> allReviews) {
        this.allReviews = allReviews;
    }

    public Company(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getAllJobs() {
        return allJobs;
    }

    public void setAllJobs(List<Job> allJobs) {
        this.allJobs = allJobs;
    }
}
