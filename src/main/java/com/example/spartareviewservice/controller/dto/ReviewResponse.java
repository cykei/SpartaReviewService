package com.example.spartareviewservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReviewResponse {
    private long id;
    private long userId;
    private int score;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
}
