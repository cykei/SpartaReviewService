package com.example.spartareviewservice.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequest {
    private long userId;
    private int score;
    private String content;
}
