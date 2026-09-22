package com.shawarmashop.tests.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestockResponse {
    private Integer id;
    private String name;
    private Integer onHand;
    private Integer totalCost;
    private String message;
}
