package com.javanotes.notes.service;

import com.javanotes.notes.dto.CategoryDto;

import java.util.List;

public interface CategoryService
{
    List<CategoryDto> findAllCategories();
}
