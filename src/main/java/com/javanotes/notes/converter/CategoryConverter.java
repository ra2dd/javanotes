package com.javanotes.notes.converter;

import com.javanotes.notes.dto.CategoryDto;
import com.javanotes.notes.models.Category;
import com.javanotes.notes.repository.CategoryRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverter implements Converter<String, CategoryDto>
{

    @Override
    public CategoryDto convert(String source)
    {
        long parsedId = Long.parseLong(source);
        CategoryDto categoryDto = new CategoryDto(parsedId, null);

        return categoryDto;
    }
}
