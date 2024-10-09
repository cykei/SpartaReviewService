package com.example.spartareviewservice.domain;

import com.example.spartareviewservice.controller.dto.ReviewRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long productId;
    private Long userId;
    private int score;
    private String content;
    private String imageUrl;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public int getScore() {
        return score;
    }

    public static Review fromEntity(ReviewRequest reviewRequest, long productId) {
        return new Review(
                productId,
                reviewRequest.getUserId(),
                reviewRequest.getScore(),
                reviewRequest.getContent()
        );
    }

    private Review(long productId, long userId, int score, String content) {
        this.productId = productId;
        this.userId = userId;
        this.score = score;
        this.content = content;
    }
}
