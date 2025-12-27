package com.examly.springapp.service;

import com.examly.springapp.model.Category;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    private final Map<Long, Category> categories = new LinkedHashMap<>();
    private long id = 1;

    public Category addCategory(Category category) {
        category.setCategoryId(id);
        categories.put(id, category);
        id++;
        return category;
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories.values());
    }

    public Category getCategoryById(Long id) {
        return categories.get(id);
    }
}
