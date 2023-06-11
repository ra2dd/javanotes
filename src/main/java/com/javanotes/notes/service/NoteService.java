package com.javanotes.notes.service;

import com.javanotes.notes.dto.CategoryDto;
import com.javanotes.notes.dto.NoteDto;
import com.javanotes.notes.models.Note;

import java.util.List;

public interface NoteService
{
    List<NoteDto> findAllNotes();

    Note saveNote(NoteDto noteDto);

    NoteDto findNoteById(long noteId);

    void updateNote(NoteDto note);

    void deleteNote(long noteId);

    List<NoteDto> searchNotes(String query);

    void assignCategoriesToNote(NoteDto noteDto);
}
