package com.javanotes.notes.controller;

import com.javanotes.notes.dto.CategoryDto;
import com.javanotes.notes.models.Category;
import com.javanotes.notes.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/categories/new")
    public String createCategoryForm(Model model)
    {
        Category category = new Category();
        model.addAttribute("category", category);
        return "category-create";
    }

    @PostMapping("/categories/new")
    public String saveCategory(@Valid @ModelAttribute("category") CategoryDto categoryDto, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "category-create";
        }
        categoryService.saveCategory(categoryDto);
        return "redirect:/categories";
    }
}
