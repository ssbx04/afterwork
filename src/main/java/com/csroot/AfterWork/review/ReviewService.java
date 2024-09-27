package com.csroot.AfterWork.review;

import java.util.List;

public interface ReviewService {
    List<Review> findAllReviews(Long id);
    boolean createReview(Long companyId,Review review);
    Review getReview(Long companyId, Long reviewId);
    boolean updateReview(Long companyId, Long reviewId, Review updatedReview);
    boolean deleteReview(Long companyId, Long reviewId);
}
