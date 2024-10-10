package com.example.spartareviewservice.service;

import com.example.spartareviewservice.controller.dto.ProductReviewResponse;
import com.example.spartareviewservice.controller.dto.ReviewRequest;
import com.example.spartareviewservice.domain.ImageUploader;
import com.example.spartareviewservice.domain.Product;
import com.example.spartareviewservice.domain.Review;
import com.example.spartareviewservice.repository.ProductRepository;
import com.example.spartareviewservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final ImageUploader imageUploader;

    @Transactional(readOnly = true)
    public ProductReviewResponse getProductsReview(long productId, long cursor, Pageable page) {
        // 1. 상품Id 에 해당하는 리뷰를 모두 가져온다.
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        List<Review> reviews = reviewRepository.findReviewByProductIdAndIdLessThanOrderByCreatedAtDescIdDesc(productId, cursor, page);

        // 2. 평균점수를 구한다.
        long totalCount = product.getReviewCount();
        double avgScore = product.getScore();

        // 3. 조합해서 response 를 만든다
        return new ProductReviewResponse(
                totalCount,
                avgScore,
                cursor,
                reviews
        );
    }

    @Transactional
    public void createReview(long productId, ReviewRequest reviewRequest , MultipartFile image) {
        // 1. 상품이 있는지 확인
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));


        // 2. 상품에대해 리뷰를 이미 남긴적 있는지 확인 -> 인덱스로 처리합니다.
        Review review = Review.from(reviewRequest, productId);
        try {
            review = reviewRepository.save(review);
        } catch (DataIntegrityViolationException error) {
          throw new IllegalArgumentException("상품에 대한 리뷰는 한가지만 남기실 수 있습니다.");
        }

        // 3. 상품정보 업데이트
        product.updateReview(review);

        // 4. 이미지 등록
        if (!Objects.isNull(image)) {
            String imageUrl = imageUploader.uploadImage(image);
            review.setImageUrl(imageUrl);
        }
    }
}
