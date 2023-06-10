package com.javanotes.notes.dto;

import com.javanotes.notes.models.Note;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto
{
    private Long id;

    @NotEmpty(message = "You need to provide Category title.")
    private String name;

    private List<Note> notes;
}
