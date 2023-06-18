package com.javanotes.notes.dto;

import com.javanotes.notes.models.Category;
import com.javanotes.notes.models.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    @Size(min = 3, max = 20, message = "Title should be in range 3 - 20 characters.")
    private String title;

    @NotEmpty(message = "You need to provide Note content.")
    @Size(min = 3, max = 500, message = "Note content should be in range 3 - 500 characters.")
    private String content;

    private String url;
    private LocalDateTime createTime;

    private UserEntity createdBy;

    private List<CategoryDto> categories;
}
