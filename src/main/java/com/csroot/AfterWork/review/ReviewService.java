package com.csroot.AfterWork.review;

import java.util.List;

public interface ReviewService {
    List<Review> findAllReviews(Long id);
    void createReview(Long companyId,Review review);
    Review getReview(Long id);
    boolean updateReview(Long id, Review updatedReview);
    boolean deleteReview(Long id);
}
