package com.javanotes.notes.mapper;

import com.javanotes.notes.dto.CategoryDto;
import com.javanotes.notes.dto.NoteDto;
import com.javanotes.notes.models.Category;
import com.javanotes.notes.models.Note;

import java.util.stream.Collectors;

import static com.javanotes.notes.mapper.CategoryMapper.mapToCategory;
import static com.javanotes.notes.mapper.CategoryMapper.mapToCategoryDto;


public class NoteMapper
{
    public static Note mapToNote(NoteDto noteDto)
    {
        if(noteDto.getCategories() == null)
        {
            Note noteMapped = Note.builder()
                    .id(noteDto.getId())
                    .title(noteDto.getTitle())
                    .content(noteDto.getContent())
                    .url(noteDto.getUrl())
                    .createTime(noteDto.getCreateTime())
                    .createdBy(noteDto.getCreatedBy())
                    .build();
            return noteMapped;
        }
        else
        {
            Note noteMapped = Note.builder()
                    .id(noteDto.getId())
                    .title(noteDto.getTitle())
                    .content(noteDto.getContent())
                    .url(noteDto.getUrl())
                    .createTime(noteDto.getCreateTime())
                    .categories(noteDto.getCategories().stream().map((categoryDto) -> mapToCategory(categoryDto)).collect(Collectors.toList()))
                    .createdBy(noteDto.getCreatedBy())
                    .build();
            return noteMapped;
        }
    }

    public static NoteDto mapToNoteDto(Note note)
    {
        if(note.getCategories() == null)
        {
            NoteDto noteDtoMapped = NoteDto.builder()
                    .id(note.getId())
                    .title(note.getTitle())
                    .content(note.getContent())
                    .url(note.getUrl())
                    .createTime(note.getCreateTime())
                    .createdBy(note.getCreatedBy())
                    .build();
            return noteDtoMapped;
        }
        else
        {
            NoteDto noteDtoMapped = NoteDto.builder()
                    .id(note.getId())
                    .title(note.getTitle())
                    .content(note.getContent())
                    .url(note.getUrl())
                    .createTime(note.getCreateTime())
                    .createdBy(note.getCreatedBy())
                    .categories(note.getCategories().stream().map((category) -> mapToCategoryDto(category)).collect(Collectors.toList()))
                    .build();
            return noteDtoMapped;
        }
    }
}
