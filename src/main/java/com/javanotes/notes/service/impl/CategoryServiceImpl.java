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

import static com.javanotes.notes.mapper.CategoryMapper.mapToCategory;
import static com.javanotes.notes.mapper.CategoryMapper.mapToCategoryDto;

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

    /*
        Method for listing categories
     */
    @Override
    public List<CategoryDto> findAllCategories()
    {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map((category) -> mapToCategoryDto(category)).collect(Collectors.toList());
    }


    /*
        Method for creating new categories
     */
    @Override
    public Category saveCategory(CategoryDto categoryDto)
    {
        Category category = mapToCategory(categoryDto);
        return categoryRepository.save(category);
    }
}
