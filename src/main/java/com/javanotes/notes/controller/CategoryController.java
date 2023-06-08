package com.javanotes.notes.controller;

import com.javanotes.notes.dto.CategoryDto;
import com.javanotes.notes.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoryController
{
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String listCategories(Model model)
    {
        List<CategoryDto> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);
        return "categories-list";
    }
}
