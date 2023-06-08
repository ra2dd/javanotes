package com.javanotes.notes.controller;

import com.javanotes.notes.service.CategoryService;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryController
{
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }


}
