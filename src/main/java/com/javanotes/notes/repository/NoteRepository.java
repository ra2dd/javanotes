package com.javanotes.notes.repository;

import com.javanotes.notes.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long>
{

    @Query("SELECT n from Note n where n.title LIKE CONCAT('%', :query, '%')")
    List<Note> querySearchNotes(String query);

    @Query(value = "SELECT note.* FROM note JOIN users ON note.created_by = users.id WHERE note.created_by = :userId ORDER BY create_time ASC", nativeQuery = true)
    List<Note> queryOrderCreateTimeAsc(@Param("userId") Long userId);

    @Query(value = "SELECT note.* FROM note JOIN users ON note.created_by = users.id WHERE note.created_by = :userId ORDER BY create_time DESC", nativeQuery = true)
    List<Note> queryOrderCreateTimeDesc(@Param("userId") Long userId);
}
