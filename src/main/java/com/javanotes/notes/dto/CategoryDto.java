package com.javanotes.notes.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto
{
    private Long id;

    @NotEmpty(message = "You need to provide Category title.")
    private String name;
}
