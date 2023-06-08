package com.javanotes.notes.service.impl;

import com.javanotes.notes.repository.CategoryRepository;
import com.javanotes.notes.repository.NoteRepository;
import com.javanotes.notes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
