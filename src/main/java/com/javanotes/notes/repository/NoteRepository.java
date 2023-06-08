package com.javanotes.notes.repository;

import com.javanotes.notes.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long>
{

    @Query("SELECT n from Note n where n.title LIKE CONCAT('%', :query, '%')")
    List<Note> querySearchNotes(String query);
}
