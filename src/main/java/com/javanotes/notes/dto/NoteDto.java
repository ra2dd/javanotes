package com.javanotes.notes.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

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
}
