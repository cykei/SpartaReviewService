package com.example.spartareviewservice.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequest {
    private long userId;

    @Max(5)
    @Min(1)
    private int score;

    private String content;
}
