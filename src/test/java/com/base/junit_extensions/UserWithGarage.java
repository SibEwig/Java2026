package com.base.junit_extensions;

import lombok.Data;

import java.util.List;

@Data
public class UserWithGarage {
    private List<Car> cars;
    private String name;
}
