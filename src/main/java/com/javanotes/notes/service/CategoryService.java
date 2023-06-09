package com.javanotes.notes.service;

import com.javanotes.notes.dto.CategoryDto;
import com.javanotes.notes.models.Category;

import java.util.List;

public interface CategoryService
{
    List<CategoryDto> findAllCategories();

    Category saveCategory(CategoryDto categoryDto);
}
