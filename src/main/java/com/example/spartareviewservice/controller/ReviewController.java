package com.example.spartareviewservice.controller;

import com.example.spartareviewservice.controller.dto.ProductReviewResponse;
import com.example.spartareviewservice.controller.dto.ReviewRequest;
import com.example.spartareviewservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ReviewController {
    final private ReviewService reviewService;

    @GetMapping("{productId}/reviews")
    public ProductReviewResponse getProductsReview(@PathVariable long productId, @RequestParam long cursor, @RequestParam(defaultValue = "10") int size) {
        return reviewService.getProductsReview(productId, cursor, PageRequest.ofSize(size));
    }

    @PostMapping("{productId}/reviews")
    public void createReview(@PathVariable long productId, @RequestPart ReviewRequest reviewRequest, @RequestPart(required = false) MultipartFile image) {
        reviewService.createReview(productId, reviewRequest, image);
    }
}
