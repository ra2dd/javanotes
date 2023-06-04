package com.javanotes.notes.service;

import com.javanotes.notes.dto.NoteDto;
import com.javanotes.notes.models.Note;

import java.util.List;

public interface NoteService
{
    List<NoteDto> findAllNotes();

    Note saveNote(Note note);

    NoteDto findNoteById(long noteId);
}
