package com.javanotes.notes.dto;

import com.javanotes.notes.models.Category;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class NoteDto
{
    private Long id;
    @NotEmpty(message = "You need to provide Note title.")
    private String title;
    @NotEmpty(message = "You need to provide Note content.")
    private String content;
    private String url;
    private LocalDateTime createTime;

    private List<CategoryDto> categories;
}
