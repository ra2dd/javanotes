package com.javanotes.notes.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NoteDto
{
    private Long id;
    private String title;
    private String content;
    private String url;
    private LocalDateTime createTime;
}
