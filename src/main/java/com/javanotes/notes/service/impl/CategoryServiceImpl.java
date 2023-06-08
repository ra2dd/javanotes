package com.javanotes.notes.service.impl;

import com.javanotes.notes.dto.CategoryDto;
import com.javanotes.notes.models.Category;
import com.javanotes.notes.repository.CategoryRepository;
import com.javanotes.notes.repository.NoteRepository;
import com.javanotes.notes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService
{
    private NoteRepository noteRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(NoteRepository noteRepository, CategoryRepository categoryRepository)
    {
        this.noteRepository = noteRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> findAllCategories()
    {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map((category) -> mapToCategoryDto(category)).collect(Collectors.toList());
    }


    private Category mapToCategory(CategoryDto categoryDto)
    {
        Category categoryMapped = Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
        return categoryMapped;
    }

    private CategoryDto mapToCategoryDto(Category category)
    {
        CategoryDto categoryDtoMapped = CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
        return categoryDtoMapped;
    }
}
