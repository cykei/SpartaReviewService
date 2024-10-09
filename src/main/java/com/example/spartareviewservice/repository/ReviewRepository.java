package com.example.spartareviewservice.repository;

import com.example.spartareviewservice.domain.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
   List<Review> findReviewByProductIdAndIdLessThanOrderByCreatedAtDesc(int productId, long cursor, Pageable pageable);
}
