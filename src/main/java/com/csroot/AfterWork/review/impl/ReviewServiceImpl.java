package com.csroot.AfterWork.review.impl;

import com.csroot.AfterWork.review.Review;
import com.csroot.AfterWork.review.ReviewRepository;
import com.csroot.AfterWork.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    final ReviewRepository reviewRepository;
    public ReviewServiceImpl(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }
    @Override
    public List<Review> findAllReviews(Long id) {
        return reviewRepository.findByCompanyId(id);
    }

    @Override
    public void createReview(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public Review getReview(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateReview(Long id, Review updatedReview) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if(optionalReview.isPresent()){
            Review review = optionalReview.get();
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long id) {
        try {
            reviewRepository.deleteById(id);
            return  true;
        }catch (Exception e){
            return false;
        }
    }
}
