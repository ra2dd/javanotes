package com.javanotes.notes.repository;

import com.javanotes.notes.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long>
{

}
