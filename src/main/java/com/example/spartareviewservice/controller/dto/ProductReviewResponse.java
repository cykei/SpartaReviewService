package com.example.spartareviewservice.controller.dto;

import com.example.spartareviewservice.domain.Review;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductReviewResponse {
    private long totalCount;
    private double score;
    private long cursor;
    private List<ReviewResponse> reviews;

    public ProductReviewResponse(long totalCount, double score, long cursor, List<Review> reviews) {
        this.totalCount = totalCount;
        this.score = score;
        this.cursor = cursor;
        this.reviews = toReviews(reviews);
    }

    private List<ReviewResponse> toReviews(List<Review> reviews) {
        List<ReviewResponse> reviewResponses = new ArrayList<>(
                reviews.stream()
                        .map(it -> new ReviewResponse(it.getId(), it.getUserId(), it.getScore(), it.getContent(), it.getImageUrl(), it.getCreatedAt()))
                        .collect(Collectors.toList())
        );
        return reviewResponses;
    }
}
