package com.service;

import java.util.List;
import java.util.Locale.Category;

public interface CategoryService {

    public com.milind.model.Category createCategory(String name,Long userId) throws Exception;

    public List<com.milind.model.Category> findCategoryByRestaurantId(Long Id) throws Exception;

    public com.milind.model.Category findCategoryById(Long id) throws Exception;

    public com.milind.model.Category createCategory(com.milind.model.Category category, Long name, Long id);
}
