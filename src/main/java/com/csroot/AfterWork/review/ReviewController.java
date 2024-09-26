package com.csroot.AfterWork.review;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies/{id}/reviews")
public class ReviewController {
    final ReviewService reviewService;
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }
}
