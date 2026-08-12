package com.lambdas;

import java.util.List;

record Page<T>(
        List<T> items,
        int page,
        int pageSize,
        long totalCount
) {
}
