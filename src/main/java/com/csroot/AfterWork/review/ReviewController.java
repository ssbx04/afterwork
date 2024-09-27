package com.csroot.AfterWork.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review){
        boolean addState = reviewService.createReview(companyId,review);
        if (addState){
            return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Review not added", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> findReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        Review review = reviewService.getReview(companyId,reviewId);
        if (review != null){
            return new ResponseEntity<>(review,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review updatedReview){
        boolean updateState = reviewService.updateReview(companyId,reviewId,updatedReview);
        if (updateState)
            return new ResponseEntity<>("Review successfully updated",HttpStatus.OK);
        return new ResponseEntity<>("Review not updated",HttpStatus.NOT_FOUND);

    }
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        boolean deleteState = reviewService.deleteReview(companyId,reviewId);
        if(deleteState)
            return new ResponseEntity<>("Review successfully deleted",HttpStatus.OK);
        return new ResponseEntity<>("Review not deleted",HttpStatus.NOT_FOUND);
    }
}
