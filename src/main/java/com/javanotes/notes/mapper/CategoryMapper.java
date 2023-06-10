package com.javanotes.notes.mapper;

import com.javanotes.notes.dto.CategoryDto;
import com.javanotes.notes.models.Category;

import java.util.stream.Collectors;

import static com.javanotes.notes.mapper.NoteMapper.mapToNote;
import static com.javanotes.notes.mapper.NoteMapper.mapToNoteDto;

public class CategoryMapper
{
    public static Category mapToCategory(CategoryDto categoryDto)
    {
        Category categoryMapped = Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
                //.notes(categoryDto.getNotes().stream().map((noteDto) -> mapToNote(noteDto)).collect(Collectors.toList()))

        return categoryMapped;
    }

    public static CategoryDto mapToCategoryDto(Category category)
    {
        CategoryDto categoryDtoMapped = CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
                //.notes(category.getNotes().stream().map((note) -> mapToNoteDto(note)).collect(Collectors.toList()))
        return categoryDtoMapped;
    }
}
