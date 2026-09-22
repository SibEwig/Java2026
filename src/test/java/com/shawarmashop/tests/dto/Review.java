package com.shawarmashop.tests.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private String id;
    private Integer stars;
    private String text;
    private Author author;
    private Instant createdAt;
}
