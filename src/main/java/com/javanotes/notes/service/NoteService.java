package com.javanotes.notes.service;

import com.javanotes.notes.dto.NoteDto;

import java.util.List;

public interface NoteService
{
    List<NoteDto> findAllNotes();
}
