package com.csroot.AfterWork.review.impl;

import com.csroot.AfterWork.company.Company;
import com.csroot.AfterWork.company.CompanyService;
import com.csroot.AfterWork.review.Review;
import com.csroot.AfterWork.review.ReviewRepository;
import com.csroot.AfterWork.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    final ReviewRepository reviewRepository;
    final CompanyService companyService;
    public ReviewServiceImpl(CompanyService companyService, ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }
    @Override
    public List<Review> findAllReviews(Long id) {
        return reviewRepository.findByCompanyId(id);
    }

    @Override
    public boolean createReview(Long companyId,Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return  true;
        }
        return  false;
    }
    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews
                .stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }
    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        Optional<Review> optionalReview = reviewRepository
                .findByCompanyId(companyId)
                .stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst();
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
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId) != null
                && reviewRepository.existsById(reviewId)){
            if (reviewRepository.findById(reviewId).isPresent()){
                Review review = reviewRepository.findById(reviewId).get();
                Company company = review.getCompany();
                company.getAllReviews().remove(review);
                review.setCompany(null);
                companyService.updateCompanyById(companyId,company);
            }
            return  true;
        }
        return  false;
    }
}
