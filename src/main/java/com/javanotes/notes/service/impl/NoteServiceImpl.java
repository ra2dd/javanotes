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

    @Override
    public Note saveNote(NoteDto noteDto)
    {
        Note note = mapToNote(noteDto);
        return noteRepository.save(note);
    }

    @Override
    public NoteDto findNoteById(long noteId)
    {
        Note note = noteRepository.findById(noteId).get();
        return mapToNoteDto(note);
    }

    @Override
    public void updateNote(NoteDto noteDto)
    {
        Note note = mapToNote(noteDto);
        noteRepository.save(note);
    }

    @Override
    public void deleteNote(long noteId)
    {
        noteRepository.deleteById(noteId);
    }

    @Override
    public List<NoteDto> searchNotes(String query)
    {
        List<Note> notes = noteRepository.querySearchNotes(query);
        return notes.stream().map(note -> mapToNoteDto(note)).collect(Collectors.toList());
    }


    /*
        Mappers
     */
    private Note mapToNote(NoteDto noteDto)
    {
        Note noteMapped = Note.builder()
                .id(noteDto.getId())
                .title(noteDto.getTitle())
                .content(noteDto.getContent())
                .url(noteDto.getUrl())
                .createTime(noteDto.getCreateTime())
                .build();
        return noteMapped;
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
