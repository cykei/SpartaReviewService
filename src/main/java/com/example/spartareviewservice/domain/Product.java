package com.example.spartareviewservice.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private long reviewCount;

    private double score;

    public void updateReview(Review review) {
        this.score = (score * reviewCount + review.getScore()) / (reviewCount+1);
        this.reviewCount++;
    }
}
