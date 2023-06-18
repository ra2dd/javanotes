package com.javanotes.notes.service;

import com.javanotes.notes.dto.CategoryDto;
import com.javanotes.notes.dto.NoteDto;
import com.javanotes.notes.models.Note;
import com.javanotes.notes.models.UserEntity;

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

    List<NoteDto> findAllUserNotes(UserEntity sessionUser);

    List<NoteDto> orderNotesByCreateTime(String orderCreateTimeQuery, UserEntity sessionUser);
}
