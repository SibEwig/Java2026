package com.base.jdbc;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecipeRow {
    int id;
    String name;
    String size;
    double price;
    int prepSeconds;
}
