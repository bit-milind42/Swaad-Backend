package com.service;

import java.util.List;
import java.util.Locale.Category;

public interface CategoryService {

    public Category createCategory(String name,Long userId);

    public List<Category> findCategoryByRestaurantId(Long Id) throws Exception;

    public Category findCategoryById(Long id) throws Exception;
}
