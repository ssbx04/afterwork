package com.csroot.AfterWork.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    final ReviewService reviewService;
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> findAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.findAllReviews(companyId), HttpStatus.OK);
    }
}
