package com.shawarmashop.tests.partners.bread.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreadBakery {
    private String branch;
    private String masterChef;
}
