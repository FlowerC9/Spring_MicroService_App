package com.monojit.reviewms.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId,@RequestBody Review review){
        Boolean isReviewSaved = reviewService.addReview(companyId,review);
        if(isReviewSaved)
        return  new ResponseEntity<>("Review Added Successfully",HttpStatus.OK);
        else return new ResponseEntity<>("Company not found",HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review>getReview(@PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReview(reviewId),HttpStatus.OK);
    }
    @PutMapping("/{reviewId}")
    public ResponseEntity<String>updateReview(@PathVariable Long reviewId,@RequestBody Review review){
        Boolean isReviewUpdated=reviewService.updateReview(reviewId, review);
        if(isReviewUpdated) return new ResponseEntity<>("Review Updated",HttpStatus.OK);
        else return new ResponseEntity<>("Review Updated Failed",HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String>deletedReview(@PathVariable Long reviewId){
        Boolean isReviewDeleted=reviewService.deleteReview(reviewId);
        if(isReviewDeleted) return new ResponseEntity<>("Review Deleted",HttpStatus.OK);
        else return new ResponseEntity<>("Review Deletion Failed",HttpStatus.NOT_FOUND);
    }
}
