package com.javanotes.notes.mapper;

import com.javanotes.notes.dto.CategoryDto;
import com.javanotes.notes.models.Category;

import java.util.stream.Collectors;
import com.javanotes.notes.mapper.NoteMapper

import static com.javanotes.notes.mapper.NoteMapper.mapToNote;
import static com.javanotes.notes.mapper.NoteMapper.mapToNoteDto;

public class CategoryMapper
{
    public static Category mapToCategory(CategoryDto categoryDto)
    {
        Category categoryMapped = Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .notes(categoryDto.getNotes().stream().map((noteDto) -> mapToNote(noteDto)).collect(Collectors.toList()))
                .build();
        return categoryMapped;
    }

    public static CategoryDto mapToCategoryDto(Category category)
    {
        CategoryDto categoryDtoMapped = CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .notes(category.getNotes().stream().map((note) -> mapToNoteDto(note)).collect(Collectors.toList()))
                .build();
        return categoryDtoMapped;
    }
}
