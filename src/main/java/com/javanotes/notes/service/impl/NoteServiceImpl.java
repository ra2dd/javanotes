package com.javanotes.notes.service.impl;

import com.javanotes.notes.dto.NoteDto;
import com.javanotes.notes.models.Note;
import com.javanotes.notes.repository.NoteRepository;
import com.javanotes.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService
{
    private NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository)
    {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<NoteDto> findAllNotes()
    {
        List<Note> notes = noteRepository.findAll();
        return notes.stream().map((note) -> mapToNoteDto(note)).collect(Collectors.toList());
    }

    private NoteDto mapToNoteDto(Note note)
    {
        NoteDto noteDto = NoteDto.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .url(note.getUrl())
                .createTime(note.getCreateTime())
                .build();
        return noteDto;
    }
}
